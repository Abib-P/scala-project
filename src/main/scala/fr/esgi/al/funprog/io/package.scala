package fr.esgi.al.funprog

import fr.esgi.al.funprog.cli.{CliArgument, CliArgumentValue}

package object io {
  def getCliArgumentValue(cliArgs: Map[CliArgument, CliArgumentValue], cliArgument: CliArgument): List[CliArgumentValue] = {
    cliArgs.get(cliArgument)
      .map(value => value.getMandatoryCmdArgument()
        .flatMap(arg => getCliArgumentValue(cliArgs, arg)).appended(value))
      .getOrElse(List())
  }
}
