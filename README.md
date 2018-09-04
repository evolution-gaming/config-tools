# Config tools [![Build Status](https://travis-ci.org/evolution-gaming/config-tools.svg)](https://travis-ci.org/evolution-gaming/config-tools) [![Coverage Status](https://coveralls.io/repos/evolution-gaming/config-tools/badge.svg)](https://coveralls.io/r/evolution-gaming/config-tools) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/4f7d71390d8949fd83a4382a6db57f1b)](https://www.codacy.com/app/evolution-gaming/config-tools?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=evolution-gaming/config-tools&amp;utm_campaign=Badge_Grade) [ ![version](https://api.bintray.com/packages/evolutiongaming/maven/config-tools/images/download.svg) ](https://bintray.com/evolutiongaming/maven/config-tools/_latestVersion) [![License: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

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
