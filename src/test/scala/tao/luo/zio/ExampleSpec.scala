package tao.luo.zio

import tao.luo.zio.ZIO101.{goShopping, greet}
import zio._
import zio.duration.durationInt
import zio.test.Assertion._
import zio.test.TestAspect.nonFlaky
import zio.test._
import zio.test.environment.{TestClock, TestConsole}

object ExampleSpec extends DefaultRunnableSpec {
  def spec: ZSpec[_root_.zio.test.environment.TestEnvironment, Any] = suite("ExampleSpec")(
    testM("ZIO.succeed succeeds with specified value") {
      assertM(ZIO.succeed(1 + 1))(equalTo(2))
    } @@ nonFlaky,
    testM("testing an effect using map operator") {
      ZIO.succeed(1 + 1).map(n => assert(n)(equalTo(2)))
    },
    testM("testing an effect using a for comprehension") {
      for {
        n <- ZIO.succeed(1 + 1)
      } yield assert(n)(equalTo(2))
    },
    testM("fails") {
      for {
        exit <- ZIO.effect(1 / 0).catchAll(_ => ZIO.fail(())).run
      } yield assert(exit)(fails(isUnit))
    },
    testM("greet says hello to the user") {
      for {
        _ <- TestConsole.feedLines("Jane")
        _ <- greet
        value <- TestConsole.output
      } yield assert(value)(equalTo(Vector("Hello! What is your name?\n", "Hello, Jane, welcome to ZIO!\n")))
    },
    testM("goShopping delays for one hour") {
      for {
        fiber <- goShopping.fork
        _ <- TestClock.adjust(1.hour)
        _ <- fiber.join
      } yield assertCompletes
    }
  )
}
