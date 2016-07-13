package br.edu.ufcg.cspa

import akka.actor._
import scala.collection._
import Message._

/**
 * Gerencia os processos
 */
class ProcessManager extends Actor with ActorLogging {

  private val processPool: mutable.Map[String, ActorRef] = mutable.Map()

  processPool("STOP") = STOP("STOP")
  processPool("SKIP") = SKIP("SKIP")

  def receive: Receive = {
    case Create(name, proc) => persistProcess(name, proc)
    case Run(name)          => runProcess(name)
    case Shutdown           => shutdown()
  }
  

  private def runProcess(name: String) = {
    try {
      processPool(name) ! Start
    } catch {
      case e: NoSuchElementException => log.error(e, "")
    }
  }
  

  private def persistProcess(name: String, proc: String) = {
    try {
      tryPersistProcess(name, proc)
    } catch {
      case e: IllegalArgumentException => log.error(e, "")
      case e: NoSuchElementException   => log.error(e, "")
    }
  }
  

  private def tryPersistProcess(name: String, proc: String) = {
    if (name.equalsIgnoreCase("stop") || name.equalsIgnoreCase("skip"))
      throw new IllegalArgumentException("User defined processes can not be named 'SKIP' or 'STOP'")
    else
      processPool += (name -> processOf(name, proc))
  }

  private def eventOf(a: String) = {
    if (!a.charAt(0).isLower)
      throw new IllegalArgumentException("Events name must start with a alphabetic lowercase character")
    else
      SingleEvent(a)
  }

  private def processOf(name: String, proc: String) = proc match {
    case "STOP" => STOP(name)
    case "SKIP" => SKIP(name)
    case _ =>
      if (isValid(proc))
        createPrefix(name, proc)
      else
        throw new IllegalArgumentException("Could not match given string with a process")
  }

  def isValid(procString: String) = {
    val regex = "(\\s*[a-z]+\\s*->)+\\s*[A-Z]+\\s*"
    procString.matches(regex)
  }

  private def createPrefix(name: String, procString: String): ActorRef = {
    val cleanProc = procString.trim().replace("\\s", "")
    var eventAndProcesses = cleanProc.split("->").toList

    //Lida com a recursão simples
    //A recursão simples está limitada a 100 vezes
    if (name == eventAndProcesses.last) {
      val initEventAndProcesses = eventAndProcesses.init
      var newEventAndProcesses: List[String] = eventAndProcesses.init
      for (x <- 1 until 100) {
        newEventAndProcesses = newEventAndProcesses ++ initEventAndProcesses
      }
      newEventAndProcesses = newEventAndProcesses ++ List("STOP")
      eventAndProcesses = newEventAndProcesses
    }
    
    Prefix(name, eventOf(eventAndProcesses.head), createPrefixAcc(eventAndProcesses.tail))

  }

  //Change context
  private def createPrefixAcc(evtProcs: List[String]): ActorRef = evtProcs match {
    case x :: Nil =>
      processPool(x)
    case x :: xs =>
      Prefix(eventOf(x), createPrefixAcc(xs))
  }

  private def shutdown() = {
    context.system.terminate()
  }

}

object ProcessManager {
  def apply()(implicit as: ActorSystem) = {
    as.actorOf(Props[ProcessManager], "ProcessManager")
  }
}