package fr.esgi.al.funprog

package object cli {
  private def parseCmdArgument(args: List[String], cmdArgument: CmdArgument): Map[CmdArgument, CmdArgumentValue] = {
    args.find(arg => arg.startsWith(cmdArgument.getName())).flatMap(arg =>
      cmdArgument.getPossibleValue()
        .flatMap(possibleValue => possibleValue.find(argValue => argValue.getName() == arg.split("=")(1))
          .orElse(
            if (possibleValue.isEmpty)
              Option(CmdArgumentValueInput(arg.split("=")(1)))
            else
              Option(CmdArgumentValueInvalid(arg.split("=")(1)))
          ))
        .orElse(Option(CmdArgumentValueInvalid(arg.split("=")(1))))
    ).map(argValue => Map(cmdArgument -> argValue))
      .getOrElse(Map())
  }

  def parseCmdArgument(args: List[String], cmdArgument: List[CmdArgument]): Map[CmdArgument, CmdArgumentValue] = {
    cmdArgument.flatMap(arg => parseCmdArgument(args, arg)).toMap
  }
}
