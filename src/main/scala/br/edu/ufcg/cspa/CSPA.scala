package br.edu.ufcg.cspa

import akka.actor._
import akka.actor.SupervisorStrategy._
import Message._

/*
 * TODO: É possível indicar a linha que causou uma exceção no log?
 * TODO: Mudar momento em que os processos são criados para permitir recursões.
 * O ideal é que os processos sejam efetivamente criados na primeira execução do run
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
    create("new1", "a->SKIP")
    create("new3", "a->b->STOP")
    create("A", "a->SKIP")
    create("B", "c->A")
    create("C", "c->C")

    run("st")
    run("sk")
    run("new1")
    run("new3")
    run("B")

    shutdown()

  }

  def create(name: String, proc: String) = {
    manager ! Create(name, proc)
  }

  def run(name: String) = {
    manager ! Run(name)
  }

  def shutdown() = {
    manager ! Shutdown
  }

}
