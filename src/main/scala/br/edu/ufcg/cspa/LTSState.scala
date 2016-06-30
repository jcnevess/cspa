package br.edu.ufcg.cspa

trait LTSState {
  val transition: SimpleEvent
  val name: String
  val nextProcess: Process
}

case class RegularState(transition: SimpleEvent, name: String, nextProcess: Process) extends LTSState {}

case class StartState(nextProcess: Process) extends LTSState {
  val transition = SimpleEvent("Nothing") //TODO refatorar
  val name = "START"
}

case class OmegaState(transition: SimpleEvent) extends LTSState {
  val nextProcess = SimpleProcess("Nothing") //TODO refatorar
  val name = "OMEGA"
}

case class SkipState(transition: SimpleEvent) extends LTSState {
  val nextProcess = SKIP //TODO refatorar
  val name = "SKIP"
}