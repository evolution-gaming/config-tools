package com.evolutiongaming.config

import com.evolutiongaming.config.ConfigHelper._
import com.typesafe.config.{Config, ConfigException, ConfigFactory}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should._

import scala.concurrent.duration._

class ConfigHelperSpec extends AnyFunSuite with Matchers {

  test("get") {
    val config = ConfigFactory.parseString("key:value")
    config.get[String]("key") shouldEqual "value"
    intercept[Exception] {
      config.get[Int]("key")
    }
  }

  test("return boolean") {
    val config = ConfigFactory.parseString("key:true")
    config.get[Boolean]("key") shouldEqual true
  }

  test("return config") {
    val config = ConfigFactory.parseString("key:{key: value}")
    config.get[Config]("key").getString("key") shouldEqual "value"
  }

  test("return duration") {
    val config = ConfigFactory.parseString("key:1m")
    config.get[FiniteDuration]("key") shouldEqual 1.minute
  }

  for {
    str <- List(
      "key:[1,2]",
      "key:[1 ,2 ]",
      "key:[ 1 , 2 ]",
      """key:"1 ,2 """",
      "key: 1; 2")
  } {
    val config = ConfigFactory.parseString(str)

    test(s"return int list from $str") {
      config.get[List[Int]]("key") shouldEqual List(1, 2)
    }

    test(s"return long list from $str") {
      config.get[List[Long]]("key") shouldEqual List(1l, 2l)
    }
  }

  for {
    str <- List(
      """key:"" """,
      """key:" " """)
  } {

    val config = ConfigFactory.parseString(str)

    test(s"return empty int list from $str") {
      config.get[List[Int]]("key") shouldEqual Nil
    }

    test(s"return empty long list from $str") {
      config.get[List[Long]]("key") shouldEqual Nil
    }

    test(s"return empty double list from $str") {
      config.get[List[Double]]("key") shouldEqual Nil
    }

    test(s"return empty boolean list from $str") {
      config.get[List[Boolean]]("key") shouldEqual Nil
    }

    test(s"return empty string list from $str") {
      config.get[List[String]]("key") shouldEqual Nil
    }
  }

  for {
    str <- List(
      "key:[str]",
      "key:str",
      """key:"str" """)
  } {
    val config = ConfigFactory.parseString(str)

    test(s"return failure parsing int list from $str") {
      the[ConfigException.WrongType] thrownBy config.get[List[Int]]("key")
    }

    test(s"return failure parsing long list from $str") {
      the[ConfigException.WrongType] thrownBy config.get[List[Long]]("key")
    }

    test(s"return failure parsing double list from $str") {
      the[ConfigException.WrongType] thrownBy config.get[List[Double]]("key")
    }

    test(s"return failure parsing boolean list from $str") {
      the[ConfigException.WrongType] thrownBy config.get[List[Boolean]]("key")
    }
  }

  for {
    str <- List(
      "key:[1s,2s]",
      """key:"1s,2s"""",
      "key:1s;2s")
  } {
    test(s"return duration list from $str") {
      val config = ConfigFactory.parseString(str)
      config.get[List[FiniteDuration]]("key") shouldEqual List(1.second, 2.second)
    }
  }

  for {
    str <- List(
      "key:[1.1,2.2]",
      """key:"1.1,2.2"""",
      "key:1.1;2.2")
  } {
    test(s"return double list from $str") {
      val config = ConfigFactory.parseString(str)
      config.get[List[Double]]("key") shouldEqual List(1.1, 2.2)
    }
  }

  for {
    str <- List(
      "key:[a,b]",
      """key:"a,b"""",
      "key:a;b")
  } {
    test(s"return string list from $str") {
      val config = ConfigFactory.parseString(str)
      config.get[List[String]]("key") shouldEqual List("a", "b")
    }
  }

  for {
    str <- List(
      "key:[true,false]",
      """key:"true,false"""",
      "key:true;false")
  } {
    test(s"return boolean list from $str") {
      val config = ConfigFactory.parseString(str)
      config.get[List[Boolean]]("key") shouldEqual List(true, false)
    }
  }

  test("getOpt") {
    val config = ConfigFactory.parseString("key1:value1\nkey2:value2")
    config.getOpt[String]("key1") shouldEqual Some("value1")
    config.getOpt[String]("unknown") shouldEqual None
    config.getOpt[String]("key2", "key1") shouldEqual Some("value2")
    config.getOpt[String]("key", "key1") shouldEqual Some("value1")
    intercept[Exception] {
      config.getOpt[Long]("key1")
    }
  }

  test("getOrElse") {
    val config = ConfigFactory.parseString("k1:s\nk2:1")
    config.getOrElse[String]("k1", "k2") shouldEqual "s"
    config.getOrElse[String]("unknown", "k2") shouldEqual "1"
    intercept[Exception] {
      config.getOrElse[FiniteDuration]("k1", "k2")
    }
  }

  test("getPrefixed") {
    val config = ConfigFactory.parseString("prefix.key:v1\nkey:v2")
    config.getPrefixed[String]("key", "prefix") shouldEqual "v1"
    config.getPrefixed[String]("key", "unknown") shouldEqual "v2"
    intercept[Exception] {
      config.getPrefixed[Double]("key", "prefix")
    }
  }
}
