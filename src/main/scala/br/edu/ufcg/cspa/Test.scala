package br.edu.ufcg.cspa

import CSPA._

object Test {
  
  def main(args: Array[String]) {

    "st" |:| "STOP"
    "sk" |:| "SKIP"
    "new1" |:| "a->a->SKIP"
    "new2" |:| "a->STOP"
    "new3" |:| "a->b->c->STOP"
    "new4" |:| "a->b->c->SKIP"
    "A" |:| "a->SKIP"
    "B" |:| "b->A"
    "C" |:| "c->C"
    "D" |:| "d->D"
    "E" |:| "e->E"
    "F" |:| "f->F"

    "A"|=|"B"
    "A"|~|"B"

    run("st")
    run("sk")
    run("new1")
    run("new2")
    run("new3")
    run("new4")
    run("B")
    run("C")
    run("D")
    run("E")
    run("F")
    

  }
  
}