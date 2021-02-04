name := "zio-in-action"

version := "0.1"

scalaVersion := "2.13.4"

scalacOptions += "-Ymacro-annotations"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio"          % "1.0.4",
  "dev.zio" %% "zio-macros"   % "1.0.4",
  "dev.zio" %% "zio-test"     % "1.0.4",
  "dev.zio" %% "zio-test-sbt" % "1.0.4",
)