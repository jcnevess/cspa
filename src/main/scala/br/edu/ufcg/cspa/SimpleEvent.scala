package br.edu.ufcg.cspa

import akka.actor._

case class SimpleEvent(name: String) extends Event[Nothing] {
  def inputEvent(name: String, value: Nothing)(context: Process) =
    throw new UnsupportedOperationException("Event with arity 0")
  def outputEvent(name: String)(context: Process) =
    throw new UnsupportedOperationException("Event with arity 0")
  def simpleEvent(value: Nothing)(context: Process) =
    throw new UnsupportedOperationException("Event with arity 0")
  
  //TODO melhorar sintaxe do operador (DSL?)
  /*def prefix(process: ActorRef)(implicit as: ActorSystem) = new Prefix("", this, process)
  def ->(process: ActorRef)(implicit as: ActorSystem)  = prefix(process)*/
  
  override def toString(): String = name
}
