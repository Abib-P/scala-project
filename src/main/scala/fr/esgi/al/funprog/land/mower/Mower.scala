package fr.esgi.al.funprog.land.mower

import fr.esgi.al.funprog.land.Instructions.{D, A, G, Instruction}
import fr.esgi.al.funprog.land.mower.Orientation.Orientation

case class Mower(initialPosition: Position, initialOrientation: Orientation) {

  private val position: Position = initialPosition
  private val orientation: Orientation = initialOrientation

  def getPosition: Position = position

  def getOrientation: Orientation = orientation

  private def moveForward(): Mower = {
    new Mower(position.moveForward(orientation), orientation)
  }

  private def turnLeft(): Mower = {
    new Mower(position, Orientation.turnLeft(orientation))
  }

  private def turnRight(): Mower = {
    new Mower(position, Orientation.turnRight(orientation))
  }

  def move(instruction: Instruction): Mower = {
    instruction match {
      case A => moveForward()
      case G => turnLeft()
      case D => turnRight()
    }
  }

  def print(): Unit = {
    println(s"Mower: ${position.getX.toString} ${position.getY.toString} ${orientation.toString}")
  }

}
