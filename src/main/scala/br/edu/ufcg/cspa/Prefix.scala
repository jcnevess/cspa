package br.edu.ufcg.cspa

import akka.actor._
import Message._

/**
 * Represents a prefixed event
 * @author Julio
 */
class Prefix(val firstEvent: SingleEvent,
             val nextProcess: ActorRef) extends Process {

  var trace: List[SingleEvent] = ???
  var states: List[State] = ???
  
  def receive: Receive = ???
  
}

object Prefix {
  def apply(name: String, firstEvent: SingleEvent, nextProcess: ActorRef)(implicit ac: ActorContext): ActorRef = {
    ac.actorOf(Props(classOf[Prefix], name, firstEvent, nextProcess))
  }
}