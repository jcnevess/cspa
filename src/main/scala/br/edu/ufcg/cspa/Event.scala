package br.edu.ufcg.cspa

trait Event[T] {
  val name: String
  
  def inputEvent(name: String, value: T)(context: Process): Unit
  def outputEvent(name: String)(context: Process): T
  def simpleEvent(value: T)(context: Process): T

  def ?(name: String, value: T)(context: Process): Unit = inputEvent(name, value)(context)
  def !(name: String)(context: Process): T = outputEvent(name)(context)
  def $(value: T)(context: Process): T = simpleEvent(value)(context)
}

case class SimpleEvent(name: String) extends Event[Nothing] {
  override def inputEvent(name: String, value: Nothing)(context: Process) =
    throw new UnsupportedOperationException("Event with arity 0")
  override def outputEvent(name: String)(context: Process) =
    throw new UnsupportedOperationException("Event with arity 0")
  override def simpleEvent(value: Nothing)(context: Process) =
    throw new UnsupportedOperationException("Event with arity 0")
}

/*case class Channel[T]() extends Event[T] {
  override def inputEvent(name: String, value: T)(context: Process) =
    context.getValuesPool.add(name, value)
  override def outputEvent(name: String)(context: Process) =
    context.getValuesPool.get(name).asInstanceOf[T]
  override def simpleEvent(value: T)(context: Process) = value
}*/