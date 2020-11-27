val Versions = new {
  val TensorFlow = "0.2.0"
  val ScalaTest = "3.0.1"
}

name := "spark-scala-messing-about"

version := "0.1.0"

scalaVersion := "2.11.12"

libraryDependencies ++= List(
  "org.tensorflow"    %  "tensorflow-core-platform"    % Versions.TensorFlow,
  "org.scalatest"     %% "scalatest"                   % Versions.ScalaTest % "test"
)

parallelExecution in Test := false
