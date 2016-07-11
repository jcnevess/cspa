package br.edu.ufcg.cspa

import akka.actor._

object Message {
  case class Perform(trace: List[SingleEvent], states: List[State])
  case object Start
  case class Create(name: String, strRepr: String)
  case class Run(name: String)
  case object Shutdown
}