//package fr.esgi.al.funprog.land.mower
//
//import fr.esgi.al.funprog.land.mower.Orientation.Orientation
//
//class Mower(initialPosition: Position, initialOrientation: Orientation) {
//  private def position: Position = initialPosition
//  private var orientation: Orientation = initialOrientation
//
//  def getPosition: Position = position
//
//  def getOrientation: Orientation = orientation
//
//  def moveForward(): Unit = {
//    position = position.moveForward(orientation)
//  }
//
//  def turnLeft(): Unit = {
//    orientation = Orientation.turnLeft(orientation)
//  }
//
//  def turnRight(): Unit = {
//    orientation = Orientation.turnRight(orientation)
//  }
//}
