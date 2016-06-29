package br.edu.ufcg.cspa

case class SimpleProcess(val name: String) extends Process {
  
    def this() = this("")
    
    def receive: Receive = {
      ???
    }
}