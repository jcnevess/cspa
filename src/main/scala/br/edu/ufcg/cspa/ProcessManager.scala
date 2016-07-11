package br.edu.ufcg.cspa

import akka.actor._
import scala.collection._
import Message._

/**
 * Gerencia os processos 
 */
class ProcessManager extends Actor with ActorLogging {
  
  private val processPool: mutable.Map[String, ActorRef] = mutable.Map()
  
  private def eventOf(a: String) = {
    if(!a.charAt(0).isLower)
      throw new IllegalArgumentException("Events name must start with a alphabetic lowercase character")
    else
      SingleEvent(a)
  }
  
  
  private def processOf(name: String, proc: String) = proc match {
    case "STOP" => STOP(name)
    case "SKIP" => SKIP(name)
    case _ => throw new IllegalArgumentException("Could not match given string with a process")
  }
  
  
  private def tryPersistProcess(name: String, proc: String) = {
    if(name.equalsIgnoreCase("stop") || name.equalsIgnoreCase("skip"))
      throw new IllegalArgumentException("User defined processes can not be named 'SKIP' or 'STOP'")
    else
      try {
        processPool += (name -> processOf(name, proc))
      } catch {
        case e: IllegalArgumentException => throw e
      }
   }
  
  
  private def persistProcess(name: String, proc: String) = {
    try {
      tryPersistProcess(name, proc)
    } catch {
      case e: IllegalArgumentException => log.error(e, "")
    }
  }
  
  
  private def shutdown() = {
    context.system.terminate()
  }
  
  
  def receive: Receive = {
    case Create(name, proc) => persistProcess(name, proc)
    case Run(name) => processPool(name) ! Start
    case Shutdown => shutdown()
  }  
  
}

object ProcessManager {
  def apply()(implicit as: ActorSystem) = {
    as.actorOf(Props[ProcessManager])
  }
}