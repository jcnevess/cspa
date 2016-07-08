package br.edu.ufcg.cspa

import akka.actor._

class ProcessCreator extends Actor {
  
  def receive: Receive = ???
  
}

object ProcessCreator {
  def apply()(implicit ac: ActorContext) = {
    ac.actorOf(Props[ProcessCreator])
  }
}