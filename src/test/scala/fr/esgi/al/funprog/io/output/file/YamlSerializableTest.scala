package fr.esgi.al.funprog.io.output.file

import fr.esgi.al.funprog.land.Instructions._
import fr.esgi.al.funprog.land.{LandResult, MowerHandler}
import fr.esgi.al.funprog.land.mower.{Mower, Orientation, Position}
import org.scalatest.funsuite.AnyFunSuite

class YamlSerializableTest extends AnyFunSuite {
  test("should serialize as yaml") {
    val result = YamlSerializable.serializeLandResult.serialize(
      LandResult(
        5,
        5,
        List(
          MowerHandler(
            Mower(
              Position(1, 2),
              Orientation.North
            ),
            List(G, A, G, A, G, A, G, A, A),
            Mower(Position(1, 3), Orientation.North)
          ),
          MowerHandler(
            Mower(
              Position(3, 3),
              Orientation.East
            ),
            List(A, A, D, A, A, D, A, D, D, A),
            Mower(Position(5, 1), Orientation.East)
          )
        )
      ),
      1
    )

    assert(result == """limite:
                       |  x: 5
                       |  y: 5
                       |tondeuses:
                       |- debut:
                       |    point:
                       |      x: 1
                       |      y: 2
                       |    direction: N
                       |  instructions:
                       |    - G
                       |    - A
                       |    - G
                       |    - A
                       |    - G
                       |    - A
                       |    - G
                       |    - A
                       |    - A
                       |  fin:
                       |    point:
                       |      x: 1
                       |      y: 3
                       |    direction: N
                       |- debut:
                       |    point:
                       |      x: 3
                       |      y: 3
                       |    direction: E
                       |  instructions:
                       |    - A
                       |    - A
                       |    - D
                       |    - A
                       |    - A
                       |    - D
                       |    - A
                       |    - D
                       |    - D
                       |    - A
                       |  fin:
                       |    point:
                       |      x: 5
                       |      y: 1
                       |    direction: E
                       |
                       |""".stripMargin)
  }
}
