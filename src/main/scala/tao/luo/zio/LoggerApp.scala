package tao.luo.zio

import tao.luo.log.Logger
import zio.console.putStrLn
import zio.{ExitCode, RIO, ZEnv, ZIO}

object LoggerApp extends zio.App {
  override def run(args: List[String]): ZIO[ZEnv, Nothing, ExitCode] = {
    val program = for {
      _ <- makeProgram()
    } yield ()

    program.foldM(
      err => putStrLn(s"Execution failed with: ${err.getMessage}").exitCode,
      _ => ZIO.succeed(ExitCode.success)
    )
  }

  // format: off
  private def makeProgram(): ZIO[ZEnv, Throwable, Unit] = {
    val loggerLayer = Logger.console

    //    val test = Logger.info("hello")

    val test: RIO[Logger.Logger, Unit] = for {
      _ <- Logger.info("hello")
      _ <- Logger.info("tao")
    } yield ()

    test.provideSomeLayer[ZEnv](loggerLayer)
  }
}
