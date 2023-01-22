package fr.esgi.al.funprog.argument.input

abstract class CmdArgument(name: String, possibleValue: List[CmdArgumentValue]) {

  def getName(): String = name

  def getPossibleValue(): List[CmdArgumentValue] = possibleValue
}

case class CmdArgumentInputType() extends CmdArgument("--input-type", List[CmdArgumentValue](CmdArgumentValueInputTypeFile(), CmdArgumentValueInputTypeCmd()))
case class CmdArgumentInputSrc() extends CmdArgument("--input-src", List())

case class CmdArgumentOutputType() extends CmdArgument("--output-type", List[CmdArgumentValue](CmdArgumentValueOutputTypeFile(), CmdArgumentValueOutputTypeCmd()))

case class CmdArgumentOutputSrc() extends CmdArgument("--output-src", List())