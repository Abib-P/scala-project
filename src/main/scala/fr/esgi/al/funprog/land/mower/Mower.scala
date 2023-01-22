package fr.esgi.al.funprog.land.mower

import fr.esgi.al.funprog.land.mower.Orientation.Orientation

class Mower(initialPosition: Position, initialOrientation: Orientation) {

  private val position: Position = initialPosition
  private val orientation: Orientation = initialOrientation

  def getPosition: Position = position

  def getOrientation: Orientation = orientation

  def moveForward(): Mower = {
    new Mower(position.moveForward(orientation), orientation)
  }

  def turnLeft(): Mower = {
    new Mower(position.moveForward(orientation), orientation)
  }

  def turnRight(): Mower = {
    new Mower(position.moveForward(orientation), orientation)
  }
}
