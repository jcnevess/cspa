package br.edu.ufcg.cspa

import akka.actor.ActorLogging

/**
 * Represents the special process STOP
 * @author Julio
 */
object STOP extends Process {

  override def receive(): Receive = ???
  
  override def toString(): String = "STOP"

}