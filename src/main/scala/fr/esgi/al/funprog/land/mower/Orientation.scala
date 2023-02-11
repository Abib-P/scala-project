package fr.esgi.al.funprog.land.mower

object Orientation extends Enumeration {
  type Orientation = Value
  val North, South, West, East = Value

  def fromChar(c: Char): Orientation = c match {
    case 'N' => North
    case 'S' => South
    case 'W' => West
    case 'E' => East
  }

  def toChar(orientation: Orientation): Char = orientation match {
    case North => 'N'
    case South => 'S'
    case West  => 'W'
    case East  => 'E'
  }

  def turnLeft(orientation: Orientation): Orientation = {
    orientation match {
      case North => West
      case West  => South
      case South => East
      case East  => North
    }
  }

  def turnRight(orientation: Orientation): Orientation = {
    orientation match {
      case North => East
      case East  => South
      case South => West
      case West  => North
    }
  }
}
