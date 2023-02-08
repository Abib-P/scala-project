package fr.esgi.al.funprog.io.output

import fr.esgi.al.funprog.cli._
import fr.esgi.al.funprog.io.getCliArgumentValue
import fr.esgi.al.funprog.io.output.cmd.CmdOutput
import fr.esgi.al.funprog.io.output.file.JsonFileOutput

object OutputParser  {
  def parseCliArgument(cliArgs: Map[CliArgument, CliArgumentValue]): Output = {
    val inputType = getCliArgumentValue(cliArgs, CliArgumentOutputType())
    inputType match {
      case List(CliArgumentValueInput(src), CliArgumentValueOutputTypeFile()) => JsonFileOutput(src)
      case List(CmdArgumentValueOutputTypeCli()) => CmdOutput()
      case _ => CmdOutput()
    }
  }
}

