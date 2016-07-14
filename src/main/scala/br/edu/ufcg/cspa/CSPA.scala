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

  def main(args: Array[String]) {

    create("st", "STOP")
    create("sk", "SKIP")
    create("new1", "a->a->SKIP")
    create("new2", "a->STOP")
    create("new3", "a->b->c->STOP")
    create("new4", "a->b->c->SKIP")
    create("A", "a->SKIP")
    create("B", "b->A")
    create("C", "c->C")
    create("D", "d->D")
    create("E", "e->E")
    create("F", "f->F")
    
    externalChoice("A", "B")
    internalChoice("A", "B")

    run("st")
    run("sk")
    run("new1")
    run("new2")
    run("new3")
    run("new4")
    run("B")
    run("C")
    run("D")
    run("E")
    run("F")


  }

  def create(name: String, proc: String) = {
    manager ! Create(name, proc)
  }

  def run(name: String) = {
    manager ! Run(name)
  }

  def execute(procedure: => Unit) = {
    Future { procedure } onComplete {
      case _ => manager ! Shutdown
    }
  }
  
  def externalChoice(proc1: String, proc2: String) = {
    println("Escolha externa: 1 para processo 1, 2 para processo 2")
    val choice = StdIn.readInt()
    
    if(choice == 1) {
      println("O processo 1 (" + proc1 + ") será executado")
      run(proc1)
    } else {
      println("O processo 2 (" + proc2 + ") será executado")
      run(proc2)
    }
  }
  
  def internalChoice(proc1: String, proc2: String) = {
    println("Escolha interna")
    val choice = Random.nextInt(2) + 1
    
    if(choice == 1) {
      println("O processo 1 (" + proc1 + ") será executado")
      run(proc1)
    } else {
      println("O processo 2 (" + proc2 + ") será executado")
      run(proc2)
    }
  }

  /*implicit def toProcessableString(value: String): ProcessableString = ProcessableString(value)

  case class ProcessableString(name: String) {
    def <<<(proc: String) = {
      create(name, proc)
    }
    
    def unary_$(): Unit = {
      run(name)
    }
  }*/

}
