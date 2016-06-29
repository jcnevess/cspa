package br.edu.ufcg.cspa

import akka.actor.Actor
import scala.util.Random

case class Process(val name: String, private var evtStack: List[SimpleEvent]) extends Actor {
  
  
  
  def this(name: String) = this(name, Nil)
  
  def this() = this("", Nil)

  //Sync processes
  def receive(): Receive = {
    case SyncNextEvent(ev: SimpleEvent) => 
      //PerformEvent if ev is the firstEvent of this process
      if(ev == firstEvent) evtStack = evtStack.tail
  }
  
  def firstEvent() = evtStack.head 

  def prefix(ev: SimpleEvent): Process = {
    Process("", ev :: evtStack)
  }
  
  def externalChoice(proc: Process)(choice: SimpleEvent): Process = {
    if (choice == firstEvent) this
    else proc
  }
  
  def internalChoice(proc: Process): Process = {
    val choice = Random.nextInt(2)
    if (choice == 0) this
    else proc
  }

  def >>:(ev:SimpleEvent): Process = prefix(ev)
  
  def |=|(proc: Process, choice: SimpleEvent): Process = externalChoice(proc)(choice)
  
  def |~|(proc: Process): Process = internalChoice(proc)

}