package br.edu.ufcg.cspa

import akka.actor._
import akka.actor.SupervisorStrategy._
import Message._


/*
 * TODO: Implementar o gerenciador de processos que cuidara da criação e armazenamento de processos
 * TODO: Modificar o parametro dos metodos apply dos processos SKIP e STOP. Para gerar uma hierarquia, 
 * não precisamos do ActorSystem, precisamos do contexto do ator que será o pai do novo ator.
 * TODO: O actorSystem só será usado para criar o gerenciador de processos
 * TODO: Implementar condição de término do actorSystem
 */


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
