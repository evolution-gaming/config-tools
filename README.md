# Config tools [![Build Status](https://github.com/evolution-gaming/config-tools/workflows/CI/badge.svg)](https://github.com/evolution-gaming/config-tools/actions?query=workflow%3ACI) [![Coverage Status](https://coveralls.io/repos/evolution-gaming/config-tools/badge.svg)](https://coveralls.io/r/evolution-gaming/config-tools) [![Codacy Badge](https://app.codacy.com/project/badge/Grade/f795ec1f2b2f4fe99c94e154b147db8e)](https://app.codacy.com/gh/evolution-gaming/config-tools/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade) [![Version](https://img.shields.io/badge/version-click-blue)](https://evolution.jfrog.io/artifactory/api/search/latestVersion?g=com.evolutiongaming&a=config-tools_2.13&repos=public) [![License: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

## ConfigHelper.scala example

```scala
  import com.evolutiongaming.config.ConfigHelper._

  val config = ConfigFactory.load()
  
  config.get[FiniteDuration]("path") // FiniteDuration

  config.getOpt[FiniteDuration]("path") // Option[FiniteDuration]
  
  config.get[String]("path") // String
  
  ConfigFactory.parseString("key:[1,2]").get[List[Int]]("key") // List(1,2)
  
  ConfigFactory.parseString("""key:"1,2" """).get[List[Int]]("key") // List(1,2)
  
  ConfigFactory.parseString("""key:"1;2" """).get[List[Int]]("key") // List(1,2)
```

## Setup

```scala
resolvers += Resolver.bintrayRepo("evolutiongaming", "maven")

libraryDependencies += "com.evolutiongaming" %% "config-tools" % "1.0.0"
```
