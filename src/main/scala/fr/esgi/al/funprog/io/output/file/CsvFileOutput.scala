package fr.esgi.al.funprog.io.output.file

import better.files.File
import fr.esgi.al.funprog.io.Serializable
import fr.esgi.al.funprog.io.output.file.CsvSerializable.serializeMowerHandler.serialize
import fr.esgi.al.funprog.land.mower.{Mower, Orientation}
import fr.esgi.al.funprog.land.{LandResult, MowerHandler}

object CsvSerializable {
  implicit val serializeLandResult: Serializable[LandResult] =
    (landResult: LandResult, depth: Int) => {
      "numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions\n" + serializeMowerHandler
        .serialize(landResult.mowers, depth)
    }

  def serializeMower(mower: Mower): String = {
    mower.getPosition.getX.toString + ";" + mower.getPosition.getY.toString + ";" + Orientation
      .toChar(mower.getOrientation)
      .toString + ";"
  }

  implicit val serializeMowerHandler: Serializable[List[MowerHandler]] =
    (truc: List[MowerHandler], depth: Int) => {
      truc match {
        case Nil => ""
        case MowerHandler(mower, instructions, finalMower) :: tail =>
          val dd = (depth + 1).toString + ";"
          val dd8 = instructions.map(_.toString).mkString + "\n"
          dd + serializeMower(mower) + serializeMower(finalMower) + dd8 + serialize(
            tail,
            depth + 1
          )
      }
    }
}

case class CsvFileOutput(name: String) extends FileOutput(name) {
  def writeOutput(configuration: LandResult): Unit = {
    val file = File(getFile())
    file.createIfNotExists()
    file.overwrite(
      CsvSerializable.serializeLandResult.serialize(configuration, 0)
    )
    ()
  }
}
