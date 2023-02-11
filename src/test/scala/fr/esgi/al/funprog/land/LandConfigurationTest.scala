package fr.esgi.al.funprog.land

import fr.esgi.al.funprog.land.mower.{Mower, Position}
import fr.esgi.al.funprog.land.mower.Orientation.{East, North, South}
import org.scalatest.funsuite.AnyFunSuite

class LandConfigurationTest extends AnyFunSuite {
  test("should resolve instructions") {
    val config = LandConfiguration(5, 5, Map(
      Mower(Position(1, 1), North) -> List(Instructions.A, Instructions.D, Instructions.A, Instructions.G, Instructions.A),
      Mower(Position(3, 3), East) -> List(Instructions.A, Instructions.A, Instructions.D, Instructions.A, Instructions.D, Instructions.A, Instructions.D, Instructions.D, Instructions.A)
    ))
    config.resolve() match {
      case LandResult(_, _, mowers) =>
        assert(mowers(0).start == Mower(Position(1, 1), North))
        assert(mowers(0).instructions == List(Instructions.A, Instructions.D, Instructions.A, Instructions.G, Instructions.A))
        assert(mowers(0).end == Mower(Position(2, 3), North))
        assert(mowers(1).start == Mower(Position(3, 3), East))
        assert(mowers(1).instructions == List(Instructions.A, Instructions.A, Instructions.D, Instructions.A, Instructions.D, Instructions.A, Instructions.D, Instructions.D, Instructions.A))
        assert(mowers(1).end == Mower(Position(5,2),East))
    }
  }

  test("should resolve instructions with collision") {
    val config = LandConfiguration(5, 5, Map(
      Mower(Position(1, 1), South) -> List(Instructions.A, Instructions.A, Instructions.D, Instructions.A, Instructions.G, Instructions.A),
      Mower(Position(2, 3), East) -> List(Instructions.A, Instructions.A, Instructions.D, Instructions.A, Instructions.D, Instructions.A, Instructions.D, Instructions.D, Instructions.A)
    ))
    config.resolve() match {
      case LandResult(_, _, mowers) =>
        assert(mowers(0).start == Mower(Position(1, 1), South))
        assert(mowers(0).instructions == List(Instructions.A, Instructions.A, Instructions.D, Instructions.A, Instructions.G, Instructions.A))
        assert(mowers(0).end == Mower(Position(0,0), South))
        assert(mowers(1).start == Mower(Position(2, 3), East))
        assert(mowers(1).instructions == List(Instructions.A, Instructions.A, Instructions.D, Instructions.A, Instructions.D, Instructions.A, Instructions.D, Instructions.D, Instructions.A))
        assert(mowers(1).end == Mower(Position(4, 2), East))
    }
  }
}
