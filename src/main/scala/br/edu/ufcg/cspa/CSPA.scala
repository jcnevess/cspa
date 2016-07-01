package br.edu.ufcg.cspa

/**
 * @author ${user.name}
 */
object CSPA {

  def main(args : Array[String]) {

    val ev = SimpleEvent("Ev")
    val ev2 = SimpleEvent("Ev2")
    val proc1 = ev -> (ev2 -> STOP)
    val proc2 = ev -> (ev2 -> SKIP)
    
    println(proc1.start())
    println(proc2.start())
   
    
    /*val proc3 = ev >>: proc1
    val proc4 = proc3 |~| Stop
    val proc5 = proc3 |=| (Stop, ev)
    
    println(proc3)
    println(proc4)
    println(proc5)*/

  }

}
