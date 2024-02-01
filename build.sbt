name := "config-tools"

organization := "com.evolutiongaming"

homepage := Some(new URL("http://github.com/evolution-gaming/config-tools"))

startYear := Some(2017)

organizationName := "Evolution"

organizationHomepage := Some(url("http://evolution.com"))

publishTo := Some(Resolver.evolutionReleases)

scalaVersion := crossScalaVersions.value.head

crossScalaVersions := Seq("2.13.12", "2.12.18", "3.3.1")

scalacOptions in (Compile,doc) ++= Seq("-groups", "-implicits", "-no-link-warnings")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.4",
  "org.scalatest" %% "scalatest" % "3.2.14" % Test)

licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT")))

releaseCrossBuild := true

scalacOptsFailOnWarn := Some(false)
