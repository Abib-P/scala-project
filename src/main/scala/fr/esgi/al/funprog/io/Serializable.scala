package fr.esgi.al.funprog.io

trait Serializable[A] {
  def getTab(depth: Int): String = "\t" * depth
  def getSpace(depth: Int): String = " " * depth * 2
  def serialize(a: A, depth: Int): String
}
