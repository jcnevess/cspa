package br.edu.ufcg.cspa

import akka.actor._
import akka.actor.SupervisorStrategy._
import Message._
import scala.concurrent.{ ExecutionContext, Future }
import ExecutionContext.Implicits.global
import scala.io._
import scala.util.Random

/*
 * TODO: É possível indicar a linha que causou uma exceção no log?
 * TODO: Melhorar a lógica do shutdown
 */

/**
 * @author Julio
 */
object CSPA {

  implicit val system = ActorSystem("CSPA")
  val manager = ProcessManager()


  private def create(name: String, proc: String) = {
    manager ! Create(name, proc)
  }

  def run(name: String) = {
    manager ! Run(name)
  }

  private def execute(procedure: => Unit) = {
    Future { procedure } onComplete {
      case _ => manager ! Shutdown
    }
  }

  private def externalChoice(proc1: String, proc2: String): String = {
    println("External choice: 1 for process 1, 2 for process 2")
    val choice = StdIn.readInt()

    if (choice == 1) {
      println("Process 1 (" + proc1 + ") will be executed")
      run(proc1)
      proc1
    } else if (choice == 2) {
      println("Process 2 (" + proc2 + ") will be executed")
      run(proc2)
      proc2
    } else {
      throw new UnsupportedOperationException("Unknown process")
    }

  }

  private def internalChoice(proc1: String, proc2: String): String = {
    println("Internal choice")
    val choice = Random.nextInt(2) + 1

    if (choice == 1) {
      println("Process 1 (" + proc1 + ") will be executed")
      run(proc1)
      proc1
    } else {
      println("Process 2 (" + proc1 + ") will be executed")
      run(proc2)
      proc2
    }
  }

  implicit def stringToOperableString(proc: String): OperableString = new OperableString(proc)
  implicit def stringToProcessableString(name: String): ProcessableString = new ProcessableString(name)

  class OperableString(proc1: String) {
    def |=|(proc2: String): String = externalChoice(proc1, proc2)
    def |~|(proc2: String): String = internalChoice(proc1, proc2)
  }

  class ProcessableString(name: String) {
    def |:|(proc: String) = create(name, proc)
  }

}
