package br.edu.ufcg.cspa

object Tick extends Event[Nothing]{
  val name = "Tick"
  
  def inputEvent(name: String, value: Nothing)(context: Process) =
    throw new UnsupportedOperationException("Tick does not support events")
  def outputEvent(name: String)(context: Process) =
    throw new UnsupportedOperationException("Tick does not support events")
  def simpleEvent(value: Nothing)(context: Process) =
    throw new UnsupportedOperationException("Tick does not support events")
}