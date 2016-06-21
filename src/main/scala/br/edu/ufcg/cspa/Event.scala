package br.edu.ufcg.cspa

trait Event[T] {
  val context: EventContext

  def inputEvent(name: String, value: T): Unit
  def outputEvent(name: String): T
  def simpleEvent(value: T): T

  def ?(name: String, value: T): Unit = inputEvent(name, value)
  def !(name: String): T = outputEvent(name)
  def $(value: T): T = simpleEvent(value)
}

case class SimpleEvent(val context: EventContext) extends Event[Nothing] {
  override def inputEvent(name: String, value: Nothing) =
    throw new UnsupportedOperationException("Event with arity 0")
  override def outputEvent(name: String) =
    throw new UnsupportedOperationException("Event with arity 0")
  override def simpleEvent(value: Nothing) =
    throw new UnsupportedOperationException("Event with arity 0")
}

case class Channel[T](val context: EventContext) extends Event[T] {
  override def inputEvent(name: String, value: T) =
    ValuesPool.add(context.procId + "<" + name + ">", value)
  override def outputEvent(name: String) =
    ValuesPool.get(context.procId + "<" + name + ">").asInstanceOf[T]
  override def simpleEvent(value: T) = value
}
/*case class Event2[A, B]() extends Event
case class Event3[A, B, C]() extends Event
case class Event4[A, B, C, D]() extends Event
case class Event5[A, B, C, D, E]() extends Event*/