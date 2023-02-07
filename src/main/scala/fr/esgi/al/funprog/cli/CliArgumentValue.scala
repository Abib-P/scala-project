package fr.esgi.al.funprog.cli

abstract class CliArgumentValue(name: String, mandatoryCmdArgument: List[CliArgument]) {
  def getName(): String = name

  def getMandatoryCmdArgument(): List[CliArgument] = mandatoryCmdArgument
}

case class CliArgumentValueInvalid(name: String) extends CliArgumentValue(name, List())

case class CliArgumentValueInputTypeFile() extends CliArgumentValue("file", List(CliArgumentInputSrc()))

case class CmdArgumentValueInputTypeCli() extends CliArgumentValue("cmd", List())

case class CliArgumentValueInput(name: String) extends CliArgumentValue(name, List())

case class CliArgumentValueOutputTypeFile() extends CliArgumentValue("file", List(CliArgumentOutputSrc()))

case class CmdArgumentValueOutputTypeCli() extends CliArgumentValue("cmd", List())
