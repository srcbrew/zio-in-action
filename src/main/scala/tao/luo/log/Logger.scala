package tao.luo.log

import zio._
import zio.clock._
import zio.console.{Console => ConsoleZIO}
import zio.macros.accessible

@accessible
object Logger {
  type Logger = Has[Service]

  def console: URLayer[Clock with ConsoleZIO, Logger] =
    ZLayer.fromServices[Clock.Service, ConsoleZIO.Service, Service] { (clock, console) =>
      Console(clock, console)
    }

  def silent: ULayer[Logger] = ZLayer.succeed(Silent)

  trait Service {
    def trace(message: => String): UIO[Unit]

    def debug(message: => String): UIO[Unit]

    def info(message: => String): UIO[Unit]

    def warn(message: => String): UIO[Unit]

    def error(message: => String): UIO[Unit]

    def error(t: Throwable)(message: => String): UIO[Unit]
  }
}
