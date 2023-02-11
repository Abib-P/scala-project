package fr.esgi.al.funprog.land.mower

import org.scalatest.funsuite.AnyFunSuite

class OrientationTest extends AnyFunSuite {
  test("should create a orientation") {
    val orientation = Orientation.North
    assert(orientation == Orientation.North)
  }

  test("should get left") {
    assert(Orientation.turnLeft(Orientation.North) == Orientation.West)
    assert(Orientation.turnLeft(Orientation.West) == Orientation.South)
    assert(Orientation.turnLeft(Orientation.South) == Orientation.East)
    assert(Orientation.turnLeft(Orientation.East) == Orientation.North)
  }

  test("should get right") {
    assert(Orientation.turnRight(Orientation.North) == Orientation.East)
    assert(Orientation.turnRight(Orientation.East) == Orientation.South)
    assert(Orientation.turnRight(Orientation.South) == Orientation.West)
    assert(Orientation.turnRight(Orientation.West) == Orientation.North)
  }
}
