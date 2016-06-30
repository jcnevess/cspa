package br.edu.ufcg.cspa

import akka.actor.Actor
import scala.util.Random

/**
 * Base trait for implementation of processes
 * @author Julio
 */
trait Process extends Actor {
  
 def start(): List[LTSState]
 def perform(acc: List[LTSState]): List[LTSState]

}