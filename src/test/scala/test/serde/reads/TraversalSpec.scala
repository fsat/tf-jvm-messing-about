package test.serde.reads

import test.TestBase

class TraversalSpec extends TestBase {
  it("traverse shapes") {
    val shape = Seq[Long](1, 3, 4)
    val input = List(List(List(1, 2, 3), List(11, 12, 13)))

    Traversal.traverse(shape)

    fail("TODO")
  }
}
