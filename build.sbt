name := "config-tools"

organization := "com.evolutiongaming"

homepage := Some(url("https://github.com/evolution-gaming/config-tools"))

startYear := Some(2017)

organizationName := "Evolution"

organizationHomepage := Some(url("https://evolution.com"))

publishTo := Some(Resolver.evolutionReleases)

scalaVersion := crossScalaVersions.value.head

crossScalaVersions := Seq("2.13.10", "2.12.17", "3.2.1")

scalacOptions in (Compile,doc) ++= Seq("-groups", "-implicits", "-no-link-warnings")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.4",
  "org.scalatest" %% "scalatest" % "3.2.14" % Test)

licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT")))

releaseCrossBuild := true

scalacOptsFailOnWarn := Some(false)

//addCommandAlias("check", "all versionPolicyCheck Compile/doc")
addCommandAlias("check", "show version")
addCommandAlias("build", "+all compile test")
