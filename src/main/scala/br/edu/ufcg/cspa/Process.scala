package br.edu.ufcg.cspa

import akka.actor.Actor

case class Process(val evtStack: List[Event[_]]) extends Actor {

  def this() = this(Nil)

  def receive: Receive

  def prefix(ev: Event[_]): Process = {
    Process(ev :: evtStack)
  }

  def >>:(ev:Event[_]): Process = prefix(ev)

}