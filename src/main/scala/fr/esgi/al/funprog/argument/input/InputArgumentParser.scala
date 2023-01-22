package fr.esgi.al.funprog.argument.input

import fr.esgi.al.funprog.argument.ArgumentParserTrait
import fr.esgi.al.funprog.argument.input.cmd.CmdInput
import fr.esgi.al.funprog.argument.input.file.FileInput
import fr.esgi.al.funprog.argument.output.OutputArgument
import fr.esgi.al.funprog.argument.output.cmd.CmdOutput
import fr.esgi.al.funprog.argument.output.file.FileOutput

object InputArgumentParser extends ArgumentParserTrait {


  private val arguments: List[CmdArgument] = List(
    CmdArgumentInputType()
  )

  private def parse(args: Array[String], cmdArgument: CmdArgument): List[CmdArgumentValue] =
    args.find(arg => arg.startsWith(cmdArgument.getName()))
      .flatMap(arg => cmdArgument.getPossibleValue()
        .find(argValue => argValue.getName() == arg.split("=")(1))
        .orElse(Option(CmdArgumentValueInput(arg.split("=")(1)))))
      .map(argValue => argValue.getMandatoryCmdArgument()
        .flatMap(arg => parse(args, arg)).appended(argValue))
      .getOrElse(List[CmdArgumentValue]())

  def parse(args: Array[String]): List[CmdArgumentValue] = arguments.flatMap(arg => {
    parse(args, arg)
  })

  def getInput(args: Array[String]): InputArgument = {
    val list = parse(args, CmdArgumentInputType())
    list match {
      case _ :: CmdArgumentValueInputTypeFile() :: _ =>
        list match {
          case (x: CmdArgumentValueInput) :: _ =>
            new FileInput(x.getName())
          case _ => new CmdInput()
        }
      case CmdArgumentValueInputTypeCmd() :: _ => new CmdInput()
      case _ => new CmdInput()
    }
  }

  def getOutput(args: Array[String]): OutputArgument = {
    val list = parse(args, CmdArgumentOutputType())
    list match {
      case _ :: CmdArgumentValueOutputTypeFile() :: _ =>
        list match {
          case (x: CmdArgumentValueInput) :: _ =>
            new FileOutput(x.getName())
          case _ => new CmdOutput()
        }
      case CmdArgumentValueOutputTypeCmd() :: _ => new CmdOutput()
      case _ => new CmdOutput()
    }
  }


}
