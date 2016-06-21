package br.edu.ufcg.cspa

import akka.actor.ActorLogging

class CompoundProcess(val firstEvent: Event[_], val nextBehavior: Process)
  extends Process with ActorLogging {

  def receive: Receive = ???

}