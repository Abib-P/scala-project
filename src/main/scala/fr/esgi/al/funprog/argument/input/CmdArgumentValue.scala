package fr.esgi.al.funprog.argument.input

abstract class CmdArgumentValue(name: String, mandatoryCmdArgument: List[CmdArgument]) {
  def getName(): String = name

  def getMandatoryCmdArgument(): List[CmdArgument] = mandatoryCmdArgument
}

case class CmdArgumentValueInputTypeFile() extends CmdArgumentValue("file", List(CmdArgumentInputSrc()))

case class CmdArgumentValueInputTypeCmd() extends CmdArgumentValue("cmd", List())

case class CmdArgumentValueInput(name: String) extends CmdArgumentValue(name, List())

case class CmdArgumentValueOutputTypeFile() extends CmdArgumentValue("file", List(CmdArgumentOutputSrc()))

case class CmdArgumentValueOutputTypeCmd() extends CmdArgumentValue("cmd", List())
