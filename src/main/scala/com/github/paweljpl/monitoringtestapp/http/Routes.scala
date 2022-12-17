package com.github.paweljpl.monitoringtestapp.http

import kamon.Kamon
import kamon.metric.DynamicRange
import org.http4s.{HttpRoutes, Response}
import org.http4s.dsl.Http4sDsl
import zio.{Cause, Exit, Task, ZIO}
import zio.interop.catz._

object Routes extends Http4sDsl[Task] {

  private val responseCounter = Kamon.counter("http.response")

  private val divResults = Kamon.histogram("app.div.result").withoutTags()

  private def onExit(result: Exit[Throwable, Response[Task]], endpointName: String) =
    result match {
      case Exit.Success(response) =>
        ZIO.succeed(responseCounter.withTag("endpoint", endpointName).withTag("status", response.status.code).increment())
      case Exit.Failure(_)        => ZIO.unit
    }

  private val greetings = HttpRoutes.of[Task] {
    case GET -> Root / "greetings" / name                         => Ok(s"Hello $name").onExit(e => onExit(e, "/greetings/<name>/"))
    case GET -> Root / "div" / IntVar(dividend) / IntVar(divisor) =>
      ZIO
        .attempt(dividend / divisor)
        .tap(result => ZIO.attempt(divResults.record(result)))
        .flatMap(result => Ok(s"$dividend / $divisor = $result"))
        .catchAll(error => ZIO.logErrorCause(Cause.fail(error)) *> InternalServerError())
        .onExit(e => onExit(e, "/div/<dividend>/<divisor>"))
  }

  val all: HttpRoutes[Task] = greetings

}
