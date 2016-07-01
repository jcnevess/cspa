package br.edu.ufcg.cspa

import akka.actor._

/**
 * Represents the special process SKIP
 * @author Julio
 */
object SKIP extends Prefix("SKIP", Tick, STOP) {

  //override def receive(): Receive = ???

  override def start: List[LTSState] = StartState(SKIP) :: Nil
  override def perform(acc: List[LTSState]): List[LTSState] = StopState(Tick) :: acc

  override def toString() = name
}

/*object SKIP{
  def apply()(implicit as: ActorSystem): ActorRef = {
    
  }
}*/