package br.edu.ufcg.cspa

import akka.actor._

/**
 * Represents the special process STOP
 * @author Julio
 */
object STOP extends Process {

  //override def receive(): Receive = ???
  
  override def start(): List[LTSState] = StopState(SimpleEvent("Nothing")) :: Nil
  override def perform(acc: List[LTSState]): List[LTSState] = StopState(SimpleEvent("Nothing")) :: acc
  
  override def toString(): String = "STOP"
}

/*object STOP {
  def apply()(implicit as: ActorSystem): ActorRef = {
    as.actorOf(Props[STOP])
  }
}*/