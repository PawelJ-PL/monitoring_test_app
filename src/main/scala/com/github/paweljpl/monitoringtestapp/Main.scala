package com.github.paweljpl.monitoringtestapp

import com.github.paweljpl.monitoringtestapp.http.HttpServer
import kamon.Kamon
import zio.logging.backend.SLF4J
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault, ZLayer}

object Main extends ZIOAppDefault {
  Kamon.init()

  override val bootstrap: ZLayer[Any, Any, Unit] = zio.Runtime.removeDefaultLoggers >>> SLF4J.slf4j.tap(_ => ZIO.log("Starting logger"))

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = HttpServer.live

}
