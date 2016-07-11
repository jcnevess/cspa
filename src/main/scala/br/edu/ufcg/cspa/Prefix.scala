package br.edu.ufcg.cspa

import akka.actor._
import Message._

/**
 * Represents a prefixed event
 * @author Julio
 */
class Prefix(val firstEvent: SingleEvent,
             val nextProcess: ActorRef) extends Process  with ActorLogging {

  var trace: List[SingleEvent] = Nil
  var states: List[State] = Nil
  
  def receive: Receive = {
    case Perform(trace_, states_) =>
      trace = firstEvent :: trace_
      states = RegularState(nextProcess) :: states_
      nextProcess ! Perform(trace, states)
    case Start =>
      trace = firstEvent :: Nil
      states = RegularState(nextProcess) :: Nil
      nextProcess ! Perform(trace, states)      
  }
  
}

object Prefix {
  def apply(name: String, firstEvent: SingleEvent, nextProcess: ActorRef)(implicit ac: ActorContext): ActorRef = {
    ac.actorOf(Props(classOf[Prefix], firstEvent, nextProcess), name)
  }
  def apply(firstEvent: SingleEvent, nextProcess: ActorRef)(implicit ac: ActorContext): ActorRef = {
    ac.actorOf(Props(classOf[Prefix], firstEvent, nextProcess))
  }
}