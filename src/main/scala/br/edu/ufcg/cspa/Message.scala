package br.edu.ufcg.cspa

object Message {
  case class Perform(trace: List[SingleEvent], states: List[State])
  case object Start
}