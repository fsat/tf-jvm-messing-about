package test.serde.reads

import test.TestBase

object TraversalSpec {
  final case class Scenario(name: String, shape: Seq[Long], expectedCoordinates: Seq[Seq[Long]])
}

class TraversalSpec extends TestBase {
  import TraversalSpec.Scenario

  describe("makeCoordinates") {
    Seq(
      Scenario(
        name = "1d array",
        shape = Seq(1),
        expectedCoordinates = List(
          List(0))),

      Scenario(
        name = "1d array",
        shape = Seq(3),
        expectedCoordinates = List(
          List(0),
          List(1),
          List(2))),

      Scenario(
        name = "3d array",
        shape = Seq(1, 1, 1),
        expectedCoordinates = List(
          List(0, 0, 0))),

      Scenario(
        name = "3d array",
        shape = Seq(1, 2, 3),
        expectedCoordinates = List(
          List(0, 0, 0),
          List(0, 0, 1),
          List(0, 0, 2),

          List(0, 1, 0),
          List(0, 1, 1),
          List(0, 1, 2)))).foreach { s =>
        it(s"makes coordinates for ${s.name} with ${s.shape} shape") {
          val result = Traversal.createCoordinates(s.shape)
          result shouldBe s.expectedCoordinates
        }
      }

  }
}
