package br.edu.ufcg.cspa

import akka.actor.Actor

trait Process extends Actor {

  def receive: Receive

  def prefix(ev: Event[_]): Process = {
    CompoundProcess(ev, this)
    //FIXME: Recursive creation of actors?
    //Or would it be better stack events and (maybe) Stop process?
  }

  def >>:(ev:Event[_]): Process = prefix(ev)

}