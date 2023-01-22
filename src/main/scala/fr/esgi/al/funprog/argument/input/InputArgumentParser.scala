package fr.esgi.al.funprog.argument.input

import fr.esgi.al.funprog.argument.ArgumentParserTrait
import fr.esgi.al.funprog.argument.input.cmd.CmdInput
import fr.esgi.al.funprog.argument.input.file.FileInput

import java.io.ObjectInputFilter.Config

object InputArgumentParser extends ArgumentParserTrait {

  override def optionName: String = "--input-type="

  private val arguments: List[InputArgument] = List(
    new CmdInput,
    new FileInput
  )

  def parse(args: Array[String], conf: Config): Option[InputArgument] = args.find(_.startsWith(optionName)) match {
    case Some(arg) => arguments.find({ argument =>
      arg.replace(optionName, "").startsWith(argument.argumentName)
    })
    case _ => Some()
  }

}
