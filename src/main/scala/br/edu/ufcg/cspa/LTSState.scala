package br.edu.ufcg.cspa

trait LTSState {
  val transition: SimpleEvent
  val name: String
  val nextProcess: Process
}

case class RegularState(transition: SimpleEvent, nextProcess: Process) extends LTSState {
  val name = "STATE"
}

case class StartState(nextProcess: Process) extends LTSState {
  val transition = null
  val name = "START"
}

case class StopState(transition: SimpleEvent) extends LTSState {
  val nextProcess = null
  val name = "STOP"
}

case class SkipState(transition: SimpleEvent) extends LTSState {
  val nextProcess = STOP
  val name = "SKIP"
}