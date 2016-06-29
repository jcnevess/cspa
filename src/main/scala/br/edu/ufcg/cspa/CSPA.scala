package br.edu.ufcg.cspa

/**
 * @author ${user.name}
 */
object CSPA {

  def main(args : Array[String]) {

    val ev = SimpleEvent("Ev")
    val proc1 = Prefix("EvThenStop", ev, STOP)
   
    
    /*val proc3 = ev >>: proc1
    val proc4 = proc3 |~| Stop
    val proc5 = proc3 |=| (Stop, ev)
    
    println(proc3)
    println(proc4)
    println(proc5)*/

  }

}
