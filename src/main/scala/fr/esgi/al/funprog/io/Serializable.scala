package fr.esgi.al.funprog.io

trait Serializable[A] {

  def getTab(depth: Int): String = "\t" * depth
  def serialize(a: A, depth: Int): String
}
