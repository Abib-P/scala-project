package fr.esgi.al.funprog.io.output

import fr.esgi.al.funprog.io.output.cmd.CmdOutput
import fr.esgi.al.funprog.cli.{CliArgument, CliArgumentValue}

object OutputParser  {
  def parseCliArgument(cliArgs: Map[CliArgument, CliArgumentValue]): Output = {
    println(cliArgs)
    new CmdOutput()
  }
}

