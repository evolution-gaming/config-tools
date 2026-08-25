name := "config-tools"

organization := "com.evolutiongaming"

homepage := Some(url("https://github.com/evolution-gaming/config-tools"))

startYear := Some(2017)

organizationName := "Evolution"

organizationHomepage := Some(url("https://evolution.com"))

publishTo := Some(Resolver.evolutionReleases)

scalaVersion := crossScalaVersions.value.head

crossScalaVersions := Seq("2.13.18", "3.3.8")

Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.9",
  "org.scalatest" %% "scalatest" % "3.2.20" % Test,
)

licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT")))

versionPolicyIntention := Compatibility.BinaryCompatible

scalacOptsFailOnWarn := Some(false)

addCommandAlias("check", "+all scalafmtCheckRepo versionPolicyCheck Compile/doc")
addCommandAlias("fmt", "scalafmtRepo")
addCommandAlias("build", "+all compile test")
