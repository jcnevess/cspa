package br.edu.ufcg.cspa

object Message {
  case class Perform(str: String)
  case object Start
}