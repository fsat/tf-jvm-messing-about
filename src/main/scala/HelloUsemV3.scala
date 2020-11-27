import org.tensorflow.SavedModelBundle
import org.tensorflow.op.core.TensorArray

object HelloUsemV3 {
  def main(args: Array[String]): Unit = {
    val usemV3SavedPath = "model/tf-hub/usem/v3/"

    println("Loading USEMv3 bundle...")
    val usemV3Loader = SavedModelBundle.loader(usemV3SavedPath)
    println(usemV3Loader)

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
