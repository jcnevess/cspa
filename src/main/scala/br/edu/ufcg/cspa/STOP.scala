package br.edu.ufcg.cspa

import akka.actor._
import Message._

/**
 * Represents the special process STOP
 * @author Julio
 */
class STOP extends Process with ActorLogging {
  
  var trace: List[SingleEvent] = Nil
  var states: List[State] = Nil

  override def receive(): Receive = {
    case Perform(trace_, states_) =>
      trace = trace_
      states = Omega :: states_
      log.info("Omega")
      context.stop(self)
    case Start =>
      states = List(Omega)
      log.info("Omega")
      context.stop(self)
  }

  override def toString() = "STOP"
}

object STOP {
  def apply()(implicit as: ActorSystem): ActorRef = {
    as.actorOf(Props[STOP])
  }
}