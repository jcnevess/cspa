package br.edu.ufcg.cspa

/**
 * Represents a prefixed event
 * @author Julio
 */
class Prefix(val name: String,
             val firstEvent: SimpleEvent,
             val nextProcess: Process) extends Process {
  def receive: Receive = ???

  override def start(): List[LTSState] = {
    nextProcess.perform(StartState(nextProcess) :: Nil)
  }
  
  override def perform(acc: List[LTSState]): List[LTSState] = {
    nextProcess.perform(RegularState(firstEvent, "State", nextProcess) :: acc)
  }

  override def toString(): String = firstEvent.toString + " -> " + nextProcess.toString
}