package br.edu.ufcg.cspa

import akka.actor._
import Message._

/**
 * Represents a prefixed event
 * @author Julio
 */
class Prefix(val firstEvent: SingleEvent,
             val nextProcess: ActorRef) extends Process {
  
  def receive: Receive = ???/*{
    case Perform(str) => nextProcess ! Perform(str + firstEvent.toString + " -> ")
    case Start => nextProcess ! Perform(firstEvent.toString + " -> ")
  }*/
}
  
/*  def receive: Receive = {
    case Perform(acc: List[LTSState]) => nextProcess ! Perform(RegularState(firstEvent, nextProcess) :: acc)
  }

  override def start(): List[LTSState] = {
    nextProcess ! Perform(StartState(this) :: Nil)
  }
  
  override def perform(acc: List[LTSState]): List[LTSState] =  nextProcess.proc match {
    case STOP => nextProcess.proc.perform(StopState(firstEvent) :: acc)
    case SKIP => nextProcess.proc.perform(SkipState(firstEvent) :: acc)
    case _ => nextProcess.proc.perform(RegularState(firstEvent, nextProcess.proc) :: acc)
  }
  
  override def perform(acc: List[LTSState]): List[LTSState] = {
    nextProcess!Perform(acc)
  }

  override def toString(): String = firstEvent.toString + " -> " + nextProcess.toString
}*/

object Prefix {
  def apply(name: String, firstEvent: SingleEvent, nextProcess: ActorRef)(implicit as: ActorSystem): ActorRef = {
    as.actorOf(Props(classOf[Prefix], name, firstEvent, nextProcess))
  }
}