package br.edu.ufcg.cspa

import akka.actor.Actor
import scala.util.Random

trait Process extends Actor {
  


  //Sync processes
  /*def receive(): Receive = {
    case SyncNextEvent(ev: SimpleEvent) => 
      //PerformEvent if ev is the firstEvent of this process
      if(ev == firstEvent) evtStack = evtStack.tail
  }*/
  
  /*def prefix(ev: SimpleEvent): Process = {
    Prefix("", ev)
  }
  
  def >>:(ev:SimpleEvent): Process = prefix(ev)*/

}