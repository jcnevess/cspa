package br.edu.ufcg.cspa

import akka.actor._
import akka.actor.SupervisorStrategy._
import Message._

/**
 * @author Julio
 */
object CSPA {

  implicit val system = ActorSystem("CSPA")
  implicit def stringToEvent(a: String) = SingleEvent(a)

  def main(args: Array[String]) {

    val st = STOP()
    val sk = SKIP()
    //val pre = Prefix("", "a", st)

    st ! Start
    sk ! Start
    //pre ! Start

  }

}
