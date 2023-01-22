package fr.esgi.al.funprog.land.mower

object Orientation extends Enumeration {
  type Orientation = Value
  val North, South, West, East = Value

  def turnLeft(orientation: Orientation): Orientation = {
    orientation match {
      case North => West
      case West => South
      case South => East
      case East => North
    }
  }

  def turnRight(orientation: Orientation): Orientation = {
    orientation match {
      case North => East
      case East => South
      case South => West
      case West => North
    }
  }
}

