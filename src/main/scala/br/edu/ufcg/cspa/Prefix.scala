package br.edu.ufcg.cspa

case class Prefix (val name: String, 
                    val firstEvent: SimpleEvent, 
                    val process: Process) extends Process{
  def receive: Receive = ???
  
  override def toString(): String = firstEvent.toString + " -> " + process.toString
}