package br.edu.ufcg.cspa

import akka.actor.Actor

trait Process extends Actor {

  def receive: Receive

  def prefix(ev: Event): Process = {
    CompoundProcess(ev, this)
    //FIXME: Recursive creation of actors?
    //Or would it be better stack events and (maybe) Stop process?
  }

  def >>:(ev:Event): Process = prefix(ev)

  val ev = new Event0
  val proc = ev >>: ev >>: Stop

}