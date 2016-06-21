package br.edu.ufcg.cspa

trait Event[T] {
  val context: EventContext = _
  def inputEvent(name: String, value: T): Unit
  def outputEvent(name: String): T
  def ?(name: String, value: T): Unit = inputEvent(name, value)
  def !(name: String): T = outputEvent(name)
}

case class SimpleEvent() extends Event[Nothing] {
  val context = ???
  override def inputEvent(name: String, value: Nothing) =
    throw new UnsupportedOperationException("Event with arity 0")
  override def outputEvent(name: String) =
    throw new UnsupportedOperationException("Event with arity 0")
}

case class Channel[T]() extends Event[T] {
  val context = ???
  override def inputEvent(name: String, value: T) = ???
  override def outputEvent(name: String) = ???
}
/*case class Event2[A, B]() extends Event
case class Event3[A, B, C]() extends Event
case class Event4[A, B, C, D]() extends Event
case class Event5[A, B, C, D, E]() extends Event*/