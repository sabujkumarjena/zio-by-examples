package com.allevite.zioByExamples.section1
import zio._
object ZIOApps {
  val mol:UIO[Int] = ZIO.succeed(45)
  def main(args: Array[String]): Unit = {
    val runtime = Runtime.default
    given trace: Trace = Trace.empty
    Unsafe.unsafe {unsafe =>
      given u: Unsafe = unsafe
      println(runtime.unsafe.run(mol))
    }
  }
}

object BetterApp extends ZIOAppDefault {
  // provides runtime, trace, ...
  override def run = {
    ZIOApps.mol.debug
  }
}

//not required
object ManualApp extends  ZIOApp {
  override type Environment = this.type

  override def bootstrap: ZLayer[ZIOAppArgs, Any, ManualApp.type] = ???

  override implicit def environmentTag: zio.EnvironmentTag[ManualApp.type] = ???

  override def run: ZIO[ManualApp.type with ZIOAppArgs with Scope, Any, Any] = ???
}