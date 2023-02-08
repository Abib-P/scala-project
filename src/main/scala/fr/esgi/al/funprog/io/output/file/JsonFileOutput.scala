package fr.esgi.al.funprog.io.output.file

import better.files.File
import fr.esgi.al.funprog.io.Serializable
import fr.esgi.al.funprog.land.Instructions.Instruction
import fr.esgi.al.funprog.land.{LandConfiguration, LandResult}
import fr.esgi.al.funprog.land.mower.Orientation.Orientation
import fr.esgi.al.funprog.land.mower.{Mower, Orientation, Position}


object JsonSerializable {
    implicit val serializePosition: Serializable[Position] = new Serializable[Position] {
        def serialize(position: Position): String =
            s""""point": {
               |"x": ${position.getX.toString},
               |"y": ${position.getY.toString}
               |}""".stripMargin
    }

    implicit val serializeOrientation: Serializable[Orientation] = new Serializable[Orientation] {
        def serialize(orientation: Orientation): String = s""""direction": "${Orientation.toChar(orientation).toString}""""
    }

    implicit val serializeMower: Serializable[Mower] = new Serializable[Mower] {
        def serialize(mower: Mower): String =
            s"""{${
                serializePosition.serialize(mower.getPosition) + "," +
                    "" + serializeOrientation.serialize(mower.getOrientation)
            }
      }"""
    }

    implicit val serializeInstruction: Serializable[List[Instruction]] = new Serializable[List[Instruction]] {
        def serialize(instructions: List[Instruction]): String = s""""instructions": [${instructions.map(instruction => s""""${instruction.toString}"""").mkString(",")}]"""
    }

    implicit val serializeLand: Serializable[LandConfiguration] = new Serializable[LandConfiguration] {
        def serialize(landConfiguration: LandConfiguration): String =
            s"""{
               |"limite": {
               |     "x":${landConfiguration.width.toString},
               |     "y":${landConfiguration.height.toString}
               |},
               |"tondeuses": [
               |     ${landConfiguration.mowers.map(m => "{\"debut\":" + JsonSerializable.serializeMower.serialize(m._1) + "," + JsonSerializable.serializeInstruction.serialize(m._2) + "}").reduceOption((a, b) => a + "," + b).getOrElse("")}
               |     ]
               |}""".stripMargin
    }

    implicit val serializeLandResult: Serializable[LandResult] = new Serializable[LandResult] {
        def serialize(landResult: LandResult): String =
            s"""{
               |"limite": {
               |     "x":${landResult.width.toString},
               |     "y":${landResult.height.toString}
               |},
               |"tondeuses": [
               |     ${
                landResult.mowers.map(m => "{" +
                    "\"debut\":" + JsonSerializable.serializeMower.serialize(m._1) + ",\n" +
                    "" + JsonSerializable.serializeInstruction.serialize(m._2) + ",\n" +
                    "\"fin\":" + JsonSerializable.serializeMower.serialize(m._3) +
                    "\n}\n").reduceOption((a, b) => a + "," + b).getOrElse("")
            }
               |     ]
               |}""".stripMargin
    }
}

case class JsonFileOutput(name: String) extends FileOutput(name) {
    override def writeOutput(configuration: LandConfiguration, result: List[Mower]) = {
        val file = File(getFile())
        file.createIfNotExists()

        //val test = configuration.mowers.map(m => "\"debut\":" +JsonSerializable.serializeMower.serialize(m._1) +"," + JsonSerializable.serializeInstruction.serialize(m._2) ).fold("")((a,b) => a + "{" + b + "},")

        file.write(JsonSerializable.serializeLand.serialize(configuration))
        ()
    }

    def writeOutput2(configuration: LandResult) = {
        val file = File(getFile())
        file.createIfNotExists()

        //val test = configuration.mowers.map(m => "\"debut\":" +JsonSerializable.serializeMower.serialize(m._1) +"," + JsonSerializable.serializeInstruction.serialize(m._2) ).fold("")((a,b) => a + "{" + b + "},")

        file.appendLine(JsonSerializable.serializeLandResult.serialize(configuration))
        ()
    }

}
