name := """weights"""
organization := "com.example"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "ai.x" %% "play-json-extensions" % "0.10.0",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.13.0-play26",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
)


/**
  * Use Guice for dependency injection
  */
libraryDependencies ++= Seq(
  guice,
  "com.tzavellas" % "sse-guice" % "0.7.1"
)
