package fr.esgi.al.funprog.land
object Instructions extends Enumeration {
  type Instruction = Value
  val D, G, A = Value

  def fromChar(c: Char): Instruction = c match {
    case 'D' => D
    case 'G' => G
    case 'A' => A
  }

}