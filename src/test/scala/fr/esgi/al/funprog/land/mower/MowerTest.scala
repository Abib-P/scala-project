package fr.esgi.al.funprog.land.mower

import fr.esgi.al.funprog.land.Instructions
import org.scalatest.funsuite.AnyFunSuite

class MowerTest extends AnyFunSuite {
  test("should create a mower") {
    val mower = Mower(Position(1, 2), Orientation.North)
    assert(mower.getPosition.x == 1)
    assert(mower.getPosition.y == 2)
    assert(mower.getOrientation == Orientation.North)
  }

  test("should move forward") {
    val mower = Mower(Position(1, 2), Orientation.North)
    assert(mower.move(Instructions.A) == Mower(Position(1, 3), Orientation.North))
  }

  test("should turn left") {
    val mower = Mower(Position(1, 2), Orientation.North)
    assert(mower.move(Instructions.G) == Mower(Position(1, 2), Orientation.West))
  }

  test("should turn right") {
    val mower = Mower(Position(1, 2), Orientation.North)
    assert(mower.move(Instructions.D) == Mower(Position(1, 2), Orientation.East))
  }
}
