package fr.esgi.al.funprog.land.mower

import org.scalatest.funsuite.AnyFunSuite

class PositionTest extends AnyFunSuite {
  test("should create a position") {
    val position = Position(1, 2)
    assert(position.x == 1)
    assert(position.y == 2)
  }

  test("should move forward") {
    val position = Position(1, 2)
    assert(position.moveForward(Orientation.North) == Position(1, 3))
    assert(position.moveForward(Orientation.South) == Position(1, 1))
    assert(position.moveForward(Orientation.West) == Position(0, 2))
    assert(position.moveForward(Orientation.East) == Position(2, 2))
  }

  test("should get x") {
    val position = Position(1, 2)
    assert(position.getX == 1)
  }

  test("should get y") {
    val position = Position(1, 2)
    assert(position.getY == 2)
  }
}
