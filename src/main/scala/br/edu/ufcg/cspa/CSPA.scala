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
  }

}
