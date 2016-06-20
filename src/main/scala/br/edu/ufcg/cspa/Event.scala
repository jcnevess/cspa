package br.edu.ufcg.cspa

trait Event[T <: Product] {
  var elem: T

  def inputEvent(value: T): Unit
  def outputEvent(): T
  def ?(value: T): Event = inputEvent(value)
  def !(): T = outputEvent()
}

case class Event0() extends Event[Nothing] {
  var elem = ???

  override def inputEvent(value: Nothing) = throw new UnsupportedOperationException("Event with arity 0")
  override def outputEvent() = throw new UnsupportedOperationException("Event with arity 0")
}

case class Event1[A]() extends Event[Product1[A]] {
  var elem = _

  override def inputEvent(value: Product1[A]) = ???
  override def outputEvent() = ???
}
/*case class Event2[A, B]() extends Event
case class Event3[A, B, C]() extends Event
case class Event4[A, B, C, D]() extends Event
case class Event5[A, B, C, D, E]() extends Event*/