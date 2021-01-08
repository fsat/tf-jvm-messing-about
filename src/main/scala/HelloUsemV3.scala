import java.nio.ByteBuffer
import java.nio.file.Paths

import org.tensorflow.ndarray.{ NdArrays, StdArrays }
import org.tensorflow.ndarray.buffer.ByteDataBuffer
import org.tensorflow.op.Ops
import org.tensorflow.{ SavedModelBundle, Signature, Tensor }
import org.tensorflow.types.{ TFloat32, TString }

import scala.collection.JavaConverters._

object HelloUsemV3 {
  val tensorflowTextLibraries = Seq(
    "pip-libs/tensorflow_text/python/metrics/_text_similarity_metric_ops.dylib",
    "pip-libs/tensorflow_text/python/ops/_mst_ops.dylib",
    "pip-libs/tensorflow_text/python/ops/_sentencepiece_tokenizer.dylib",
    "pip-libs/tensorflow_text/python/ops/_split_merge_tokenizer.dylib",
    "pip-libs/tensorflow_text/python/ops/_whitespace_tokenizer.dylib",
    "pip-libs/tensorflow_text/python/ops/_sentence_breaking_ops.dylib",
    "pip-libs/tensorflow_text/python/ops/_constrained_sequence_op.dylib",
    "pip-libs/tensorflow_text/python/ops/_unicode_script_tokenizer.dylib",
    "pip-libs/tensorflow_text/python/ops/_regex_split_ops.dylib",
    "pip-libs/tensorflow_text/python/ops/_wordpiece_tokenizer.dylib",
    "pip-libs/tensorflow_text/python/ops/_normalize_ops.dylib")

  def main(args: Array[String]): Unit = {
    val usemV3SavedPath = "model/tf-hub/usem/v3/"

    println("Loading USEMv3 bundle...")
    // This is how you load serving tag
    val usemV3Loader = SavedModelBundle.loader(usemV3SavedPath).withTags("serve")
    println(usemV3Loader)

    // Make sure tensorflow text libraries is loaded right after USEMv3 is loaded.
    // This is because the TF libs are initialized by the SavedModelBundle.loader(), and tensorflow text libraries
    // needs the TF libs in memory before loading.
    tensorflowTextLibraries.foreach { v =>
      val path = Paths.get(v).toAbsolutePath.toString
      println(s"Loading $path")
      System.load(path)
    }

    println("Loading USEMv3...")
    val usemV3 = usemV3Loader.load()
    println(usemV3)

    try {
      // This is how you get signature similar to saved_model_cli
      val defaultFunction = usemV3.function(Signature.DEFAULT_KEY)
      val defaultFunctionSignature = defaultFunction.signature()
      println(defaultFunctionSignature)

      // Todo send tensor to USEMv3 + fetch output embeddings
      // "input": [["I like to drink tea"]]
      val inputTensor = TString.vectorOf("I like to drink tea")
      val result = usemV3.call(Map[String, Tensor[_]]("inputs" -> inputTensor).asJava).asScala
      val tfOutput = result("outputs") match {
        case v: Tensor[TFloat32 @unchecked] => v
        case _ => throw new RuntimeException("Unexpected type")
      }

      println(tfOutput.dataType())
      println(tfOutput.shape())

      val tfOutputData = tfOutput.data()

      //      tfOutputData.read(byteOutput)
      println(tfOutputData)
      val stdArray = StdArrays.array2dCopyOf(tfOutput.data())
      println(stdArray.toList.map(_.toList))
      //
      //      val tf = Ops.create()
      //      val x = tf.constant(tfOutput)
      //
      //      println("--")
      //      println(x.data())

    } finally {
      usemV3.close()
    }
  }
}
