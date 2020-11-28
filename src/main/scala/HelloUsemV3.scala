import java.nio.file.Paths

import org.tensorflow.SavedModelBundle
import org.tensorflow.op.core.TensorArray

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
    val usemV3Loader = SavedModelBundle.loader(usemV3SavedPath)
    println(usemV3Loader)

    tensorflowTextLibraries.foreach { v =>
      val path = Paths.get(v).toAbsolutePath.toString
      println(s"Loading $path")
      System.load(path)
    }

    println("Loading USEMv3...")
    val usemV3 = usemV3Loader.load()
    println(usemV3)

    try {
      println("USEMv3 graph")
      val graph = usemV3.graph()
      println(graph)

      // Todo send tensor to USEMv3 + fetch output embeddings
      // "input": [["I like to drink tea"]]

    } finally {
      usemV3.close()
    }
  }
}
