package fr.esgi.al.funprog.io.input

import fr.esgi.al.funprog.cli._
import fr.esgi.al.funprog.io.getCliArgumentValue
import fr.esgi.al.funprog.io.input.cmd.CmdInput
import fr.esgi.al.funprog.io.input.file.TextFileInput
import fr.esgi.al.funprog.land.Instructions
import fr.esgi.al.funprog.land.Instructions.Instruction
import fr.esgi.al.funprog.land.mower.{Mower, Orientation, Position}

import scala.util.Try


@SuppressWarnings(Array("org.wartremover.warts.Throw"))
object InputParser {

    def stringToLandSize(str: String): (Int, Int) = {
        str.split(" ") match {
            case Array(width, height) =>
                (Try(width.toInt).getOrElse(throw DonneesIncorectesException("Width must be a number")),
                    Try(height.toInt).getOrElse(throw DonneesIncorectesException("Height must be a number")))
            case _ => throw DonneesIncorectesException("First line of the file must land width and height sperated by a space")
        }
    }

    def stringToInstructions(str: String): List[Instruction] = {
        str.map(char => Instructions.fromChar(char)).toList
    }

    def stringToMower(str: String): Mower = {
        str.split(" ") match {
            case Array(x, y, orientation) =>
                Mower(
                    Position(
                        Try(x.toInt).getOrElse(throw DonneesIncorectesException("X position must be a number")),
                        Try(y.toInt).getOrElse(throw DonneesIncorectesException("Y position must be a number"))
                    ),
                    Orientation.fromChar(orientation(0)))
            case _ => throw DonneesIncorectesException("Line must be mower position and orientation sperated by a space")
        }
    }

    def parseCliArgument(cliArgs: Map[CliArgument, CliArgumentValue]): Input = {
        val inputType = getCliArgumentValue(cliArgs, CliArgumentInputType())
        inputType match {
            case List(CliArgumentValueInput(src), CliArgumentValueInputTypeFile()) => TextFileInput(src)
            case List(CmdArgumentValueInputTypeCli()) => CmdInput()

            case _ => throw DonneesIncorectesException("Input type is not valid")
        }
    }
}
