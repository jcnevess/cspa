package br.edu.ufcg.cspa

import akka.actor._
import akka.actor.SupervisorStrategy._
import Message._

/**
 * Represents the special process SKIP
 * @author Julio
 */
class SKIP extends Process with ActorLogging {

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

  final val defaultStrategy: SupervisorStrategy = {
    def defaultDecider: Decider = {
      case _: ActorInitializationException => Stop
      case _: ActorKilledException         => Escalate
      case _: Exception                    => Restart
    }
    OneForOneStrategy()(defaultDecider)
  }

  override def toString() = "SKIP"
}

object SKIP {
  def apply()(implicit ac: ActorContext): ActorRef = {
    ac.actorOf(Props[SKIP])
  }

  def apply(name: String)(implicit ac: ActorContext): ActorRef = {
    ac.actorOf(Props[SKIP], name)
  }
}