package br.edu.ufcg.cspa

object ValuesPool {
  val values: Map[String, Any] = Map()

  def get(key: String) = values(key)
  def add[T](key: String, value: T) = values + (key -> value)
  def remove(key: String) = values - key
}