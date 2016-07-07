package br.edu.ufcg.cspa

import akka.actor._

/**
 * Gerencia os processos 
 */
class ProcManager extends Actor {
  
  def receive = ???
  
}

object ProcManager {
  def apply(implicit as: ActorSystem) = {
    as.actorOf(Props[ProcManager])
  }
}