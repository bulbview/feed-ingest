import sbt._
import sbt.Keys._

object FeedingestBuild extends Build {

  lazy val feedingest = Project(
    id = "feed-ingest",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "feed-ingest",
      organization := "uk.co.bbc",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.10.0",
//      resolvers:= "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",
      libraryDependencies ++= Seq( 
          "com.typesafe.akka" % "akka-actor_2.10" % "2.1.0",
          "com.typesafe.akka" % "akka-camel_2.10.0-RC5" % "2.1.0-RC6",
          "org.apache.camel" % "camel-http" % "2.10.3",
          "javax.servlet" % "servlet-api" % "2.5" % "provided"
          )
    )
  )
}
