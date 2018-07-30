name := """server"""

organization := "com.fmc"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  guice,
  "org.reactivemongo" % "play2-reactivemongo_2.12" % "0.12.7-play26",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "com.amazonaws" % "aws-java-sdk" % "1.11.362"
)