package br.edu.ufcg.cspa

import akka.actor.ActorLogging

case object Stop extends Process with ActorLogging {

  def receive: Receive = ???

}