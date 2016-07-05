package br.edu.ufcg.cspa

import akka.actor._
import Message._

/**
 * Represents the special process SKIP
 * @author Julio
 */
class SKIP extends Process with ActorLogging {

  override def receive(): Receive = {
    case Perform(acc) => log.info(acc + toString())
    case Start => log.info(toString())
  }

  override def toString() = "SKIP"
}

object SKIP{
  def apply()(implicit as: ActorSystem): ActorRef = {
    as.actorOf(Props[SKIP])
  }
}