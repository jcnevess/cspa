package br.edu.ufcg.cspa

import akka.actor._
import scala.util.Random

/**
 * Base trait for implementation of processes
 * @author Julio
 */
trait Process extends Actor {
  
 def receive: Receive
 
}