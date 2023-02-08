package fr.esgi.al.funprog.io

trait Serializable[A] {
  def serialize(a: A): String
}
