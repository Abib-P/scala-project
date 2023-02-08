package fr.esgi.al.funprog.io.input

import fr.esgi.al.funprog.cli._
import fr.esgi.al.funprog.io.getCliArgumentValue
import fr.esgi.al.funprog.io.input.cmd.CmdInput
import fr.esgi.al.funprog.io.input.file.FileInput

object InputParser {

  def parseCliArgument(cliArgs: Map[CliArgument, CliArgumentValue]): Input = {
    val inputType = getCliArgumentValue(cliArgs, CliArgumentInputType())
    inputType match {
      case List(CliArgumentValueInput(src), CliArgumentValueInputTypeFile()) => FileInput(src)
      case List(CmdArgumentValueInputTypeCli()) => CmdInput()
      case _ => CmdInput()
    }
  }
}
