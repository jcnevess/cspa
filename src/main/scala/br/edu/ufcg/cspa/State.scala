package br.edu.ufcg.cspa

import akka.actor.ActorRef

trait State {
  val nextProcess: ActorRef
}

case class RegularState(nextProcess: ActorRef) extends State

case object Omega extends State {
  val nextProcess = null
}