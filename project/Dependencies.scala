import sbt._

object Dependencies {

  private val zio = "dev.zio" %% "zio" % Version.zio

  private val zioCats = "dev.zio" %% "zio-interop-cats" % Version.zioCats

  private val zioSlf4j = "dev.zio" %% "zio-logging-slf4j" % Version.zioLogging

  private val zioTest = "dev.zio" %% "zio-test" % Version.zio % Test

  private val zioTestSbt = "dev.zio" %% "zio-test-sbt" % Version.zio % Test

  private val logback = "ch.qos.logback" % "logback-classic" % Version.logback

  private val http4sServer = "org.http4s" %% "http4s-core" % Version.http4s

  private val http4sEmberServer = "org.http4s" %% "http4s-ember-server" % Version.http4s

  private val http4sDsl = "org.http4s" %% "http4s-dsl" % Version.http4s

  private val circeGeneric = "io.circe" %% "circe-generic" % Version.circe

  private val kamon = "io.kamon" %% "kamon-bundle" % Version.kamon

  private val kamonPrometheus = "io.kamon" %% "kamon-prometheus" % Version.kamon

  private val kamonHttp4s = "io.kamon" %% "kamon-http4s-0.23" % "2.2.1"

  val allDeps = Seq(
    zio,
    zioCats,
    zioSlf4j,
    zioTest,
    zioTestSbt,
    logback,
    http4sServer,
    http4sEmberServer,
    circeGeneric,
    http4sDsl,
    kamon,
    kamonPrometheus,
    kamonHttp4s
  )

}

private object Version {

  val zio = "2.0.5"

  val zioCats = "3.3.0"

  val zioLogging = "2.1.5"

  val logback = "1.4.5"

  val http4s = "0.23.16"

  val circe = "0.15.0-M1"

  val kamon = "2.5.12"

}
