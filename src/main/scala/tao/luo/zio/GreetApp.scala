package tao.luo.zio

import tao.luo.zio.ZIO101.greet
import zio.{ExitCode, URIO, console}

object GreetApp extends zio.App {
  def run(args: List[String]): URIO[console.Console, ExitCode] =
    greet.exitCode
}
