package fr.esgi.al.funprog.io.output.file

import better.files.File
import fr.esgi.al.funprog.io.Serializable
import fr.esgi.al.funprog.land.Instructions.Instruction
import fr.esgi.al.funprog.land.LandResult
import fr.esgi.al.funprog.land.mower.{Mower, Orientation}


//numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions
//1;1;2;N;1;3;N;GAGAGAGAA
//2;3;3;E;5;1;E;AADAADADDA

object CsvSerializable {
    implicit val serializeLandResult: Serializable[LandResult] = new Serializable[LandResult] {
        def serialize(landResult: LandResult, depth: Int): String = {
            "numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions\n" + serializeTruc.serialize(landResult.mowers, depth);
        }
    }
    implicit val serializeTruc: Serializable[List[(Mower, List[Instruction], Mower)]] = new Serializable[List[(Mower, List[Instruction], Mower)]] {
        def serialize(truc: List[(Mower, List[Instruction], Mower)], depth: Int): String = {
            truc match {
                case Nil => ""
                case (mower, instructions, finalMower) :: tail => {
                    val index = depth
                    val dd = (index + 1).toString + ";"
                    val dd2 = mower.getPosition.getX.toString + ";"
                    val dd3 = mower.getPosition.getY.toString + ";"
                    val dd4 = Orientation.toChar(mower.getOrientation).toString + ";"
                    val dd5 = finalMower.getPosition.getX.toString + ";"
                    val dd6 = finalMower.getPosition.getY.toString + ";"
                    val dd7 = Orientation.toChar(finalMower.getOrientation).toString + ";"
                    val dd8 = instructions.map(_.toString).mkString + "\n"
                    dd + dd2 + dd3 + dd4 + dd5 + dd6 + dd7 + dd8 + serialize(tail, depth+1)
                }
            }

        }
    }

    /*truc.map { case (mower, instructions, finalMower) => {
                    val index = truc.indexOf((mower, instructions, finalMower))
                    val dd = (index + 1).toString + ";"
                    val dd2 = mower.getPosition.getX.toString + ";"
                    val dd3 = mower.getPosition.getY.toString + ";"
                    val dd4 = Orientation.toChar(mower.getOrientation).toString + ";"
                    val dd5 = finalMower.getPosition.getX.toString + ";"
                    val dd6 = finalMower.getPosition.getY.toString + ";"
                    val dd7 = Orientation.toChar(finalMower.getOrientation).toString + ";"
                    val dd8 = instructions.map(_.toString).mkString + "\n"
                    dd + dd2 + dd3 + dd4 + dd5 + dd6 + dd7 + dd8
                }}.mkString*/
}

case class CsvFileOutput(name: String) extends FileOutput(name) {
    def writeOutput(configuration: LandResult) = {
        val file = File(getFile())
        file.createIfNotExists()
        file.overwrite(CsvSerializable.serializeLandResult.serialize(configuration, 0))
        ()
    }
}
