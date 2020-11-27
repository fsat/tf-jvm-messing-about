val Versions = new {
  val ScalaTest = "3.0.1"
}

name := "spark-scala-messing-about"

version := "0.1.0"

scalaVersion := "2.11.12"

libraryDependencies ++= List(
  "org.scalatest"     %% "scalatest"      % Versions.ScalaTest % "test"
)

parallelExecution in Test := false
