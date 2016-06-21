package br.edu.ufcg.cspa

import scala.collection.mutable.Map

object ValuesPool {
  private var values: Map[String, Any] = Map()

  def get[T](key: String) =
    try  {
      values(key)
    } catch {
      case _ : NoSuchElementException =>
        throw new Exception("Undefined variable")
    }

  def add[T](key: String, value: T) =
    if (values contains key) throw new Exception("Duplicated variable name")
    else values + (key -> value)

  def remove(key: String) = values - key
}