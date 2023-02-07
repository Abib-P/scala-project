package fr.esgi.al.funprog.io.input

import fr.esgi.al.funprog.cli._
import fr.esgi.al.funprog.io.input.cmd.CmdInput
import fr.esgi.al.funprog.io.input.file.FileInput

import scala.collection.Map

object InputParser {

  def getCliArgumentValue(cliArgs: Map[CliArgument, CliArgumentValue], cliArgument: CliArgument): List[CliArgumentValue] = {
    cliArgs.get(cliArgument)
      .map(value => value.getMandatoryCmdArgument()
        .flatMap(arg => getCliArgumentValue(cliArgs, arg)).appended(value))
      .getOrElse(List())
  }

  def parseCliArgument(cliArgs: Map[CliArgument, CliArgumentValue]): Input = {
    val inputType = getCliArgumentValue(cliArgs, CliArgumentInputType())
    inputType match {
      case List(CliArgumentValueInput(src), CliArgumentValueInputTypeFile()) => FileInput(src)
      case List(CmdArgumentValueInputTypeCli()) => CmdInput()
      case _ => CmdInput()
    }
  }
}
