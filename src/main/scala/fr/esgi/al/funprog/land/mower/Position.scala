package fr.esgi.al.funprog.land.mower

import fr.esgi.al.funprog.land.mower.Orientation.Orientation

case class Position(x: Int, y: Int) {
  def moveForward(orientation: Orientation): Position = {
    orientation match {
      case Orientation.North => Position(x, y + 1)
      case Orientation.South => Position(x, y - 1)
      case Orientation.West  => Position(x - 1, y)
      case Orientation.East  => Position(x + 1, y)
    }
  }

  def getX: Int = x
  def getY: Int = y
}
