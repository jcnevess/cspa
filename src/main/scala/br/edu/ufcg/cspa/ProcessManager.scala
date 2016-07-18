package br.edu.ufcg.cspa

import akka.actor._
import scala.collection._
import scala.concurrent.duration._
import Message._

/**
 * Manages all logic involving processes
 * @author Julio Cesar Neves
 */
class ProcessManager extends Actor with ActorLogging {

  /**
   * Persists the created processes
   */
  private val processPool: mutable.Map[String, ActorRef] = mutable.Map()

  processPool("STOP") = STOP("STOP")
  processPool("SKIP") = SKIP("SKIP")

  /**
   * Handles messages and calls corresponding methods
   */
  def receive: Receive = {
    case Create(name, proc) => persistProcess(name, proc)
    case Run(name)          => runProcess(name)
    case Shutdown           => shutdown()
  }
  

  /**
   * Runs a given process.
   * If the process can not be found, logs an error
   * @param name The name of the process to run
   */
  private def runProcess(name: String) = {
    try {
      processPool(name) ! Start
    } catch {
      case e: NoSuchElementException => log.error(e, "")
    }
  }
  

  /**
   * Persists a given process.
   * If the process does not exist or has an invalid name, logs an error
   */
  private def persistProcess(name: String, proc: String) = {
    try {
      tryPersistProcess(name, proc)
    } catch {
      case e: IllegalArgumentException => log.error(e, "")
      case e: NoSuchElementException   => log.error(e, "")
    }
  }
  

  /**
   * Add a given process to the process pool
   * @throws IllegalArgumentException if event name is similar to 'skip' or 'stop'
   */
  private def tryPersistProcess(name: String, proc: String) = {
    if (name.equalsIgnoreCase("stop") || name.equalsIgnoreCase("skip"))
      throw new IllegalArgumentException("User defined processes can not be named 'SKIP' or 'STOP'")
    else
      processPool += (name -> processOf(name, proc))
  }

  /**
   * Create an event based on the given String.
   * @param a the String which will be turned into an event
   * @throws IllegalArgumentException if event name does not start with a lowercase character
   */
  private def eventOf(a: String) = {
    if (!a.charAt(0).isLower)
      throw new IllegalArgumentException("Events name must start with a alphabetic lowercase character")
    else
      SingleEvent(a)
  }

  /**
   * Create a process using the given String
   * @param name The name of the event
   * @param proc The string to be used while creating the process
   * @throws IllegalArgumentException If proc can not be match with a process
   */
  private def processOf(name: String, proc: String) = proc match {
    case "STOP" => STOP(name)
    case "SKIP" => SKIP(name)
    case _ =>
      if (isValid(proc))
        createPrefix(name, proc)
      else
        throw new IllegalArgumentException("Could not match given string with a process")
  }

  /**
   * Verifies if a given string can be parsed into a process
   * @param procString The string to be verified
   */
  private def isValid(procString: String) = {
    val regex = "(\\s*[a-z]+\\s*->)+\\s*[A-Z]+\\s*"
    procString.matches(regex)
  }

  /**
   * Creates an process of type Prefix
   * @param name The name of the process
   * @param procString The string which will be parsed into a prefix
   */
  private def createPrefix(name: String, procString: String): ActorRef = {
    val cleanProc = procString.trim().replace("\\s", "")
    var eventAndProcesses = cleanProc.split("->").toList

    //Handles single recursion
    //Single recursion is limited to 100 recurrences
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

  private def createPrefixAcc(evtProcs: List[String]): ActorRef = evtProcs match {
    case x :: Nil =>
      processPool(x)
    case x :: xs =>
      Prefix(eventOf(x), createPrefixAcc(xs))
  }

  /**
   * Shutdown the actorSystem
   */
  private def shutdown() = {
    context.system.terminate()
  }

}

object ProcessManager {
  def apply()(implicit as: ActorSystem) = {
    as.actorOf(Props[ProcessManager], "ProcessManager")
  }
}