package fr.esgi.al.funprog.land.mower

import fr.esgi.al.funprog.land.mower.Orientation.Orientation

case class Position(x: Int, y: Int) {
  def moveForward(orientation: Orientation): Position = {
    orientation match {
      case Orientation.North => new Position(x, y + 1)
      case Orientation.South => new Position(x, y - 1)
      case Orientation.West => new Position(x - 1, y)
      case Orientation.East => new Position(x + 1, y)
    }
  }

  def getX: Int = x
  def getY: Int = y
}
