package br.edu.ufcg.cspa

import akka.actor.ActorLogging

/**
 * Represents the special process STOP
 * @author Julio
 */
object STOP extends Process {

  override def receive(): Receive = ???
  
  override def start(): List[LTSState] = OmegaState(SimpleEvent("Nothing")) :: Nil
  override def perform(acc: List[LTSState]): List[LTSState] = OmegaState(SimpleEvent("Nothing")) :: acc
  
  override def toString(): String = "STOP"

}