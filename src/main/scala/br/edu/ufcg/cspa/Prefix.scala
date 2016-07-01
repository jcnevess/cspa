package br.edu.ufcg.cspa

import akka.actor._

/**
 * Represents a prefixed event
 * @author Julio
 */
class Prefix(val name: String,
             val firstEvent: SimpleEvent,
             val nextProcess: Process) extends Process {
  //def receive: Receive = ???

  override def start(): List[LTSState] = {
    perform(StartState(this) :: Nil)
  }
  
  override def perform(acc: List[LTSState]): List[LTSState] =  nextProcess match {
    case STOP => StopState(firstEvent) :: acc
    case SKIP => nextProcess.perform(SkipState(firstEvent) :: acc)
    case _ => nextProcess.perform(RegularState(firstEvent, nextProcess) :: acc)
  }

  override def toString(): String = firstEvent.toString + " -> " + nextProcess.toString
}

/*object Prefix {
  def apply(name: String, firstEvent: SimpleEvent, nextProcess: Process)(implicit as: ActorSystem): ActorRef = {
    as.actorOf(Props(classOf[Prefix], name, firstEvent, nextProcess))
  }
}*/