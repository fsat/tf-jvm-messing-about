package test.serde.reads

import scala.annotation.tailrec

object Traversal {
  def createCoordinates(shape: Seq[Long]): Seq[Seq[Long]] = {
    def makeRanges(v: Long): Seq[Long] = {
      @tailrec
      def appendNumber(current: Long, result: Seq[Long]): Seq[Long] = {
        if (current >= v)
          result
        else
          appendNumber(current + 1, result :+ current)
      }
      appendNumber(0, Seq.empty)
    }

    val coordinates = shape
      .map(makeRanges)
      .foldRight(Seq.empty[Seq[Long]]) { (ranges, coordinates) =>
        ranges.flatMap { num =>
          if (coordinates.isEmpty)
            coordinates :+ Seq(num)
          else
            coordinates.map { y =>
              Seq(num) ++ y
            }
        }
      }

    coordinates
  }
}
