package fr.esgi.al.funprog.io.output.file

import better.files.File
import fr.esgi.al.funprog.io.Serializable
import fr.esgi.al.funprog.land.Instructions.Instruction
import fr.esgi.al.funprog.land.{LandConfiguration, LandResult}
import fr.esgi.al.funprog.land.mower.Orientation.Orientation
import fr.esgi.al.funprog.land.mower.{Mower, Orientation, Position}


object JsonSerializable {
    implicit val serializePosition: Serializable[Position] = new Serializable[Position] {
        def serialize(position: Position, depth: Int): String =
            s"""${getTab(depth)}"point": {
               |${getTab(depth + 1)}"x": ${position.getX.toString},
               |${getTab(depth + 1)}"y": ${position.getY.toString}
               |${getTab(depth)}}""".stripMargin
    }

    implicit val serializeOrientation: Serializable[Orientation] = new Serializable[Orientation] {
        def serialize(orientation: Orientation, depth: Int): String = s"""${getTab(depth)}"direction": "${Orientation.toChar(orientation).toString}""""
    }

    implicit val serializeMower: Serializable[Mower] = new Serializable[Mower] {
        def serialize(mower: Mower, depth: Int): String =
            s"""{\n${ serializePosition.serialize(mower.getPosition, depth + 1) + ",\n" + serializeOrientation.serialize(mower.getOrientation, depth + 1) + "\n" +getTab(depth) }}"""
    }

    implicit val serializeInstruction: Serializable[List[Instruction]] = new Serializable[List[Instruction]] {
        def serialize(instructions: List[Instruction], depth: Int): String = s"""${getTab(depth)}"instructions": [${instructions.map(instruction => s""""${instruction.toString}"""").mkString(",")}]"""
    }

    implicit val serializeLand: Serializable[LandConfiguration] = new Serializable[LandConfiguration] {
        def serialize(landConfiguration: LandConfiguration, depth: Int): String =
            s"""{
                   |"limite": {
                   |     "x":${landConfiguration.width.toString},
                   |     "y":${landConfiguration.height.toString}
                   |},
                   |"tondeuses": [\n${landConfiguration.mowers.map(m => getTab(depth + 1) + "{\n"+ getTab(depth+1)+"\"debut\": " + JsonSerializable.serializeMower.serialize(m._1, depth + 1) + ",\n" + JsonSerializable.serializeInstruction.serialize(m._2, depth + 1) + "}").reduceOption((a, b) => a + ",\n" + b).getOrElse("")}]
               |}""".stripMargin
    }

    implicit val serializeLandResult: Serializable[LandResult] = new Serializable[LandResult] {
        def serialize(landResult: LandResult, depth: Int): String =
            s"""{
               |${getTab(depth)}"limite": {
               |${getTab(depth+1)}"x": ${landResult.width.toString},
               |${getTab(depth+1)}"y": ${landResult.height.toString}
               |${getTab(depth)}},
               |${getTab(depth)}"tondeuses": [
               |${ landResult.mowers.map(m => getTab(depth + 1) +"{\n"+ getTab(depth + 2 ) +"\"debut\": " + JsonSerializable.serializeMower.serialize(m._1, depth + 2) + ",\n" + JsonSerializable.serializeInstruction.serialize(m._2, depth + 2) + ",\n" + getTab(depth + 2) +"\"fin\": " + JsonSerializable.serializeMower.serialize(m._3, depth + 2) + "\n"+ getTab(depth + 1) +"}").reduceOption((a, b) => a + ",\n" + b).getOrElse("")}
               |${getTab(depth)}]
               |}""".stripMargin
    }
}

case class JsonFileOutput(name: String) extends FileOutput(name) {
    override def writeOutput(configuration: LandConfiguration, result: List[Mower]) = {
        val file = File(getFile())
        file.createIfNotExists()

        //val test = configuration.mowers.map(m => "\"debut\":" +JsonSerializable.serializeMower.serialize(m._1) +"," + JsonSerializable.serializeInstruction.serialize(m._2) ).fold("")((a,b) => a + "{" + b + "},")

        file.write(JsonSerializable.serializeLand.serialize(configuration, 0))
        ()
    }

    def writeOutput2(configuration: LandResult) = {
        val file = File(getFile())
        file.createIfNotExists()

        //val test = configuration.mowers.map(m => "\"debut\":" +JsonSerializable.serializeMower.serialize(m._1) +"," + JsonSerializable.serializeInstruction.serialize(m._2) ).fold("")((a,b) => a + "{" + b + "},")

        file.overwrite(JsonSerializable.serializeLandResult.serialize(configuration, 1))
        ()
    }

}
