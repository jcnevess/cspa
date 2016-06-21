package br.edu.ufcg.cspa

import akka.actor.ActorLogging

class CompoundProcess(val firstEvent: Event[_], val nextBehavior: Process)
  extends Process /*with ActorLogging */{

  //override def receive(): Receive = ???

}