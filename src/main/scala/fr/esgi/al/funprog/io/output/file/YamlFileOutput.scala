package fr.esgi.al.funprog.io.output.file

import better.files.File
import fr.esgi.al.funprog.io.Serializable
import fr.esgi.al.funprog.io.output.file.YamlSerializable.serializeLandResult.getSpace
import fr.esgi.al.funprog.land.Instructions.Instruction
import fr.esgi.al.funprog.land.mower.Orientation
import fr.esgi.al.funprog.land.{LandResult, MowerHandler}

object YamlSerializable {
  implicit val serializeLandResult: Serializable[LandResult] =
    (landResult: LandResult, depth: Int) => {
      s"""|limite:
        |${getSpace(depth)}x: ${landResult.width.toString}
        |${getSpace(depth)}y: ${landResult.height.toString}
        |tondeuses:
        |${serializeMowerHandler.serialize(landResult.mowers, depth)}
        |""".stripMargin
    }

  implicit val serializeMowerHandler: Serializable[List[MowerHandler]] =
    (truc: List[MowerHandler], depth: Int) => {
      truc match {
        case Nil => ""
        case MowerHandler(mower, instructions, finalMower) :: tail =>
          s"""|- debut:
            |${getSpace(depth + 1)}point:
            |${getSpace(depth + 2)}x: ${mower.getPosition.getX.toString}
            |${getSpace(depth + 2)}y: ${mower.getPosition.getY.toString}
            |${getSpace(depth + 1)}direction: ${Orientation
               .toChar(mower.getOrientation)
               .toString}
            |${getSpace(depth)}instructions:
            |${serializeInstructions.serialize(instructions, depth)}
            |${getSpace(depth)}fin:
            |${getSpace(depth + 1)}point:
            |${getSpace(depth + 2)}x: ${finalMower.getPosition.getX.toString}
            |${getSpace(depth + 2)}y: ${finalMower.getPosition.getY.toString}
            |${getSpace(depth + 1)}direction: ${Orientation
               .toChar(finalMower.getOrientation)
               .toString}
            |${serializeMowerHandler.serialize(tail, depth)}""".stripMargin
      }
    }

  implicit val serializeInstructions: Serializable[List[Instruction]] =
    (instructions: List[Instruction], depth: Int) => {
      instructions match {
        case Nil => ""
        case instruction :: Nil =>
          s"${getSpace(depth + 1)}- ${instruction.toString}"
        case instruction :: tail =>
          s"${getSpace(depth + 1)}- ${instruction.toString}\n" +
            s"${serializeInstructions.serialize(tail, depth)}".stripMargin
      }
    }
}

case class YamlFileOutput(name: String) extends FileOutput(name) {
  def writeOutput(configuration: LandResult): Unit = {
    val file = File(getFile())
    file.createIfNotExists()
    file.overwrite(
      YamlSerializable.serializeLandResult.serialize(configuration, 1)
    )
    ()
  }
}
