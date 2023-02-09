package fr.esgi.al.funprog.cli

abstract class CliArgument(name: String, possibleValue: Option[List[CliArgumentValue]]) {

  def getName: String = name

  def getPossibleValue: Option[List[CliArgumentValue]] = possibleValue
}

case class CliArgumentInputType() extends CliArgument("--input-type", Some(List[CliArgumentValue](CliArgumentValueInputTypeFile(), CmdArgumentValueInputTypeCli())))
case class CliArgumentInputSrc() extends CliArgument("--input-src", Some(List()) )

case class CliArgumentOutputType() extends CliArgument("--output-type", Some(List[CliArgumentValue](CliArgumentValueOutputTypeJson(), CmdArgumentValueOutputTypeCli(), CliArgumentValueOutputTypeCsv())))

case class CliArgumentOutputSrc() extends CliArgument("--output-src", Some(List()))
