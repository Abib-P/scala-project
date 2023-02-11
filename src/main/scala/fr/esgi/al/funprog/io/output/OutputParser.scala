package fr.esgi.al.funprog.io.output

import fr.esgi.al.funprog.cli._
import fr.esgi.al.funprog.io.getCliArgumentValue
import fr.esgi.al.funprog.io.output.cmd.CmdOutput
import fr.esgi.al.funprog.io.output.file.{CsvFileOutput, JsonFileOutput, YamlFileOutput}

object OutputParser  {
  def parseCliArgument(cliArgs: Map[CliArgument, CliArgumentValue]): Output = {
    getCliArgumentValue(cliArgs, CliArgumentOutputType()) match {
      case x if x.contains(CmdArgumentValueOutputTypeCli()) => CmdOutput()
      case x if x.exists(_.isInstanceOf[CliArgumentValueInput]) && x.contains(CliArgumentValueOutputTypeJson()) =>
        JsonFileOutput(name = x.find(_.isInstanceOf[CliArgumentValueInput])
          .map(_.getName)
          .getOrElse("")
        )
      case x if x.exists(_.isInstanceOf[CliArgumentValueInput]) && x.contains(CliArgumentValueOutputTypeCsv()) =>
        CsvFileOutput(name = x.find(_.isInstanceOf[CliArgumentValueInput])
          .map(_.getName)
          .getOrElse("")
        )
      case x if x.exists(_.isInstanceOf[CliArgumentValueInput]) && x.contains(CliArgumentValueOutputTypeYaml()) =>
        YamlFileOutput(name = x.find(_.isInstanceOf[CliArgumentValueInput])
          .map(_.getName)
          .getOrElse("")
        )
      case _ => CmdOutput()
    }
  }
}

