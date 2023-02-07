package fr.esgi.al.funprog.cli

abstract class CmdArgument(name: String, possibleValue: Option[List[CmdArgumentValue]]) {

  def getName(): String = name

  def getPossibleValue(): Option[List[CmdArgumentValue]] = possibleValue
}

case class CmdArgumentInputType() extends CmdArgument("--input-type", Some(List[CmdArgumentValue](CmdArgumentValueInputTypeFile(), CmdArgumentValueInputTypeCmd())))
case class CmdArgumentInputSrc() extends CmdArgument("--input-src", Some(List()) )

case class CmdArgumentOutputType() extends CmdArgument("--output-type", Some(List[CmdArgumentValue](CmdArgumentValueOutputTypeFile(), CmdArgumentValueOutputTypeCmd())))

case class CmdArgumentOutputSrc() extends CmdArgument("--output-src", Some(List()))