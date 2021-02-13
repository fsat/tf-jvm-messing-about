package test.serde.reads

object Traversal {
  def traverse(shape: Seq[Long]): Unit = {
    println(shape)

    def makeRanges(v: Long): Seq[Long] = {
      def appendNumber(current: Long, result: Seq[Long]): Seq[Long] = {
        if (current >= v)
          result
        else
          appendNumber(current + 1, result :+ current)
      }

      appendNumber(0, Seq.empty)
    }

    val ranges = shape
      .map(makeRanges)
      .foldLeft(Seq.empty[Seq[Long]]) { (coordinates, v) =>
        println(s"--> ${coordinates} - ${v}")
        if (coordinates.isEmpty) {
          coordinates :+ v
        } else
          v.flatMap { x =>
            coordinates.map { y =>
              y :+ x
            }
          }
      }

    println(ranges)

    //    val depth = shape.length
    //
    //    def traverseCoord(coordinate: Seq[Long], v: Long): Unit = {
    //
    //    }
    //
    //    def traverseFoo(head: Option[Long], rest: Seq[Long]): Unit =
    //      head match {
    //        case None =>
    //          ()
    //        case Some(v) =>
    //          (0 to v.toInt).foreach { x =>
    //            println(s"${x} - ${rest}")
    //            //            traverseFoo(Some(x), rest.drop(1))
    //          }
    //      }
    //    traverseFoo(shape.headOption, shape.drop(1))
  }
}
