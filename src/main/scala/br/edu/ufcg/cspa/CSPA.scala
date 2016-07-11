package br.edu.ufcg.cspa

import akka.actor._
import akka.actor.SupervisorStrategy._
import Message._

/*
 * TODO: É possível indicar a linha que causou uma exceção no log?
 * TODO: Implementar condição de término do actorSystem
 * TODO: Criar branch para implementar o prefixo
 * TODO: Essa pattern pode ser util? http://doc.akka.io/docs/akka/current/contrib/aggregator.html
 */


/**
 * @author Julio
 */
object CSPA {

  implicit val system = ActorSystem("CSPA")
  val manager = ProcessManager()

  def main(args: Array[String]) {
    
    create("st", "STOP")
    create("stp", "STOPP")
    create("STOP", "STOP")
    create("SKIP", "STOPP")
    create("sk", "SKIP")
    
    run("st")
    run("sk")
    run("stp")
    
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
