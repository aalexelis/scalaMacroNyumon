name := "sbt-example"
organization := "org.scalamacros"
version := "2.0.0"

scalaVersion in ThisBuild := "2.11.8"
run <<= run in Compile in core

lazy val macros = (project in file("macros")).settings(
 libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
)

lazy val core = (project in file("core")).settings(
 libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.1.5" % "test"
) dependsOn macros
