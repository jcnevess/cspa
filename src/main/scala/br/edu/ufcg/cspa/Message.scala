package br.edu.ufcg.cspa

object Message {
  case class Perform(acc: List[LTSState])
}