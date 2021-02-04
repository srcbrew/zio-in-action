package tao.luo.zio

import zio.{URIO, ZIO}
import zio.clock.Clock
import zio.console.{Console, getStrLn, putStrLn}
import zio.duration.durationInt

object ZIO101 {
  val greet: URIO[Console, Unit] = for {
    _ <- putStrLn("Hello! What is your name?")
    name <- getStrLn.orDie // ZIO.succeed("luo")
    _ <- putStrLn(s"Hello, ${name}, welcome to ZIO!")
  } yield ()

  val goShopping: ZIO[Console with Clock, Nothing, Unit] = putStrLn("Going shopping!").delay(1.hour)
}
