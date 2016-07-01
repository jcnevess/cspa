package br.edu.ufcg.cspa

import akka.actor._

/**
 * Represents the special process STOP
 * @author Julio
 */
object STOP extends Process {

  //override def receive(): Receive = ???
  
  
  override def start(): List[LTSState] =  Nil
  override def perform(acc: List[LTSState]): List[LTSState] = acc
  
  override def toString(): String = "STOP"
}

/*object STOP {
  def apply()(implicit as: ActorSystem): ActorRef = {
    as.actorOf(Props[STOP])
  }
}*/