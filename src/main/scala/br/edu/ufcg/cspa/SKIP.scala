package br.edu.ufcg.cspa

import akka.actor._
import Message._

/**
 * Represents the special process SKIP
 * @author Julio
 */
class SKIP extends Process with ActorLogging { 
  
  implicit val as = context.system
  
  var trace: List[SingleEvent] = Nil
  var states: List[State] = Nil

  override def receive(): Receive = {
    case Perform(trace_, states_) => 
      trace = Tick :: trace_
      states = states_
      STOP() ! Perform(trace, states)
    case Start => 
      trace = List(Tick)
      STOP() ! Perform(trace, Nil)
  }

  override def toString() = "SKIP"
}

object SKIP{
  def apply()(implicit as: ActorSystem): ActorRef = {
    as.actorOf(Props[SKIP])
  }
}