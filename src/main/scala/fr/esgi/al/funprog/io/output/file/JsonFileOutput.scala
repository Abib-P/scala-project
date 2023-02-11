package fr.esgi.al.funprog.io.output.file

import better.files.File
import fr.esgi.al.funprog.io.Serializable
import fr.esgi.al.funprog.io.output.file.JsonSerializable.serializePosition.getTab
import fr.esgi.al.funprog.land.Instructions.Instruction
import fr.esgi.al.funprog.land.{LandConfiguration, LandResult, MowerHandler}
import fr.esgi.al.funprog.land.mower.Orientation.Orientation
import fr.esgi.al.funprog.land.mower.{Mower, Orientation, Position}

object JsonSerializable {
  implicit val serializePosition: Serializable[Position] =
    (position: Position, depth: Int) => s"""${getTab(depth)}"point": {
           |${getTab(depth + 1)}"x": ${position.getX.toString},
           |${getTab(depth + 1)}"y": ${position.getY.toString}
           |${getTab(depth)}}""".stripMargin

  implicit val serializeOrientation: Serializable[Orientation] =
    (orientation: Orientation, depth: Int) =>
      s"""${getTab(depth)}"direction": "${Orientation
        .toChar(orientation)
        .toString}""""

  implicit val serializeMower
      : Serializable[Mower] = (mower: Mower, depth: Int) =>
    s"""{\n${serializePosition
      .serialize(mower.getPosition, depth + 1) + ",\n" + serializeOrientation
      .serialize(mower.getOrientation, depth + 1) + "\n" + getTab(depth)}}"""

  implicit val serializeInstruction: Serializable[List[Instruction]] =
    (instructions: List[Instruction], depth: Int) =>
      s"""${getTab(depth)}"instructions": [${instructions
        .map(instruction => s""""${instruction.toString}"""")
        .mkString(",")}]"""

  implicit val serializeLand: Serializable[LandConfiguration] =
    (landConfiguration: LandConfiguration, depth: Int) =>
      s"""{
           |"limite": {
           |     "x":${landConfiguration.width.toString},
           |     "y":${landConfiguration.height.toString}
           |},
           |"tondeuses": [\n${landConfiguration.mowers
           .map(m =>
             getTab(depth + 1) + "{\n" + getTab(depth + 1) + "\"debut\": " + JsonSerializable.serializeMower
               .serialize(m._1, depth + 1) + ",\n" + JsonSerializable.serializeInstruction
               .serialize(m._2, depth + 1) + "}"
           )
           .reduceOption((a, b) => a + ",\n" + b)
           .getOrElse("")}]
           |}""".stripMargin

  implicit val serializeMowerHandler: Serializable[MowerHandler] =
    (mowerHandler: MowerHandler, depth: Int) =>
      s"""${getTab(depth)}{
           |${getTab(depth + 1) + "\"debut\": " + JsonSerializable.serializeMower
           .serialize(mowerHandler.start, depth + 1) + ",\n" + JsonSerializable.serializeInstruction
           .serialize(mowerHandler.instructions, depth + 1) + ",\n" + getTab(
           depth + 1
         ) + "\"fin\": " + JsonSerializable.serializeMower.serialize(
           mowerHandler.end,
           depth + 1
         )}
           |${getTab(depth)}}""".stripMargin

  implicit val serializeLandResult: Serializable[LandResult] =
    (landResult: LandResult, depth: Int) =>
      s"""{
           |${getTab(depth)}"limite": {
           |${getTab(depth + 1)}"x": ${landResult.width.toString},
           |${getTab(depth + 1)}"y": ${landResult.height.toString}
           |${getTab(depth)}},
           |${getTab(depth)}"tondeuses": [
           |${landResult.mowers
           .map(m =>
             JsonSerializable.serializeMowerHandler.serialize(m, depth + 1)
           )
           .reduceOption((a, b) => a + ",\n" + b)
           .getOrElse("")}
           |${getTab(depth)}]
           |}""".stripMargin
}

case class JsonFileOutput(name: String) extends FileOutput(name) {
  def writeOutput(configuration: LandResult): Unit = {
    val file = File(getFile())
    file.createIfNotExists()
    file.overwrite(
      JsonSerializable.serializeLandResult.serialize(configuration, 1)
    )
    ()
  }
}
