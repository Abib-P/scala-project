package fr.esgi.al.funprog.io.output.file

import fr.esgi.al.funprog.land.Instructions._
import fr.esgi.al.funprog.land.{LandResult, MowerHandler}
import fr.esgi.al.funprog.land.mower.{Mower, Orientation, Position}
import org.scalatest.funsuite.AnyFunSuite

class CsvSerializableTest extends AnyFunSuite {
  test("should serialize as csv") {
    val result = CsvSerializable.serializeLandResult.serialize(
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

    assert(result == """numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions
                       |2;1;2;N;1;3;N;GAGAGAGAA
                       |3;3;3;E;5;1;E;AADAADADDA
                       |""".stripMargin)
  }
}
