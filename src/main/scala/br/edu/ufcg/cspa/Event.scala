package br.edu.ufcg.cspa

/**
 * Base trait user for implementation of events
 * @author Julio 
 */
trait Event[T] {
  val name: String
  
  def inputEvent(name: String, value: T)(context: Process): Unit
  def outputEvent(name: String)(context: Process): T
  def genericEvent(value: T)(context: Process): T
  
  override def toString = name

  def ?(name: String, value: T)(context: Process): Unit = inputEvent(name, value)(context)
  def !(name: String)(context: Process): T = outputEvent(name)(context)
  def $(value: T)(context: Process): T = genericEvent(value)(context)
}