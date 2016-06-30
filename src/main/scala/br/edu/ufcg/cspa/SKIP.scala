package br.edu.ufcg.cspa

/**
 * Represents the special process SKIP
 * @author Julio
 */
object SKIP extends Prefix("SKIP", Tick, STOP) {

  override def receive(): Receive = ???

  override def start: List[LTSState] = StartState(SKIP) :: Nil
  override def perform(acc: List[LTSState]): List[LTSState] = ???

  override def toString() = ""

}