package br.edu.ufcg.cspa

import akka.actor._
import akka.actor.SupervisorStrategy._
import Message._
import scala.concurrent.{ ExecutionContext, Future }
import ExecutionContext.Implicits.global

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

    //execute {

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
      
    //} 

  }

  def create(name: String, proc: String) = {
    manager ! Create(name, proc)
  }

  def run(name: String) = {
    manager ! Run(name)
  }
  
  def execute(procedure: => Unit) = {
    Future {procedure} onComplete {
      case _ => manager ! Shutdown
    }
  }

}
