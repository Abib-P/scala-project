package fr.esgi.al.funprog

package object cli {
  private def parseCliArgument(args: List[String], cmdArgument: CliArgument): Map[CliArgument, CliArgumentValue] = {
    args.find(arg => arg.startsWith(cmdArgument.getName())).flatMap(arg =>
      cmdArgument.getPossibleValue()
        .flatMap(possibleValue => possibleValue.find(argValue => argValue.getName() == arg.split("=")(1))
          .orElse(
            if (possibleValue.isEmpty)
              Option(CliArgumentValueInput(arg.split("=")(1)))
            else
              Option(CliArgumentValueInvalid(arg.split("=")(1)))
          ))
        .orElse(Option(CliArgumentValueInvalid(arg.split("=")(1))))
    ).map(argValue => Map(cmdArgument -> argValue))
      .getOrElse(Map())
  }

  def parseCliArgument(args: List[String], cmdArgument: List[CliArgument]): Map[CliArgument, CliArgumentValue] = {
    cmdArgument.flatMap(arg => parseCliArgument(args, arg)).toMap
  }
}
