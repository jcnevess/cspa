package br.edu.ufcg.cspa

import akka.actor._
import Message._

/**
 * @author ${user.name}
 */
object CSPA {
  
  implicit val system = ActorSystem("System")
  implicit def stringToEvent(a: String) = SingleEvent(a) 

  def main(args : Array[String]) {

    val st = STOP()
    val sk = SKIP()
    //val pre = Prefix("", "a", st)
    
    st ! Start
    sk ! Start
    //pre ! Start
  }

}
