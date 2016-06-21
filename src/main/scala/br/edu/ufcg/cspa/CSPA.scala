package br.edu.ufcg.cspa

/**
 * @author ${user.name}
 */
object CSPA {

  def main(args : Array[String]) {

    val ev = SimpleEvent()
    val proc1 = Process(Nil)
    val proc2 = Process(Nil)
    
    val proc3 = ev >>: proc1
    val proc4 = proc3 |~| Stop
    val proc5 = proc3 |=| (Stop, ev)

  }

}
