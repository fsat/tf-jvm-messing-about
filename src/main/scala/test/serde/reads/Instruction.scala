package test.serde.reads

import org.tensorflow.types.family.TType

object Instruction {
  case class Array(elements: Seq[Instruction]) extends Instruction
  case class Values[T <: TType](ttype: T, rawValues: Seq[String]) extends Instruction

}

sealed trait Instruction
