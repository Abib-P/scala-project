package fr.esgi.al.funprog.io.output

import fr.esgi.al.funprog.cli._
import fr.esgi.al.funprog.io.getCliArgumentValue
import fr.esgi.al.funprog.io.output.cmd.CmdOutput
import fr.esgi.al.funprog.io.output.file.{CsvFileOutput, JsonFileOutput}

object OutputParser  {
  def parseCliArgument(cliArgs: Map[CliArgument, CliArgumentValue]): Output = {
    val inputType = getCliArgumentValue(cliArgs, CliArgumentOutputType())
    inputType match {
      case List(CmdArgumentValueOutputTypeCli()) => CmdOutput()
      case List(CliArgumentValueInput(src), CliArgumentValueOutputTypeJson()) => JsonFileOutput(src)
      case List(CliArgumentValueInput(src), CliArgumentValueOutputTypeCsv()) => CsvFileOutput(src)
      case _ => CmdOutput()
    }
  }
}

