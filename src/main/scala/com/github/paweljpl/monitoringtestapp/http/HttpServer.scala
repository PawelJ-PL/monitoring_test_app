package com.github.paweljpl.monitoringtestapp.http

import com.comcast.ip4s.IpLiteralSyntax
import kamon.http4s.middleware.server.KamonSupport
import org.http4s.HttpRoutes
import org.http4s.ember.server.EmberServerBuilder
import zio.{Task, ZIO}
import zio.interop.catz._
import org.http4s.implicits._

object HttpServer {

  private val routes: HttpRoutes[Task] = KamonSupport(Routes.all, "0.0.0.0", 8080)

  val live: ZIO[Any, Throwable, Nothing] = for {
    server <- EmberServerBuilder.default[Task].withHost(host"0.0.0.0").withPort(port"8080").withHttpApp(routes.orNotFound).build.useForever
  } yield server

}
