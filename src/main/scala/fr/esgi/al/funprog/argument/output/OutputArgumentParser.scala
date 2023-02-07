package fr.esgi.al.funprog.argument.output

import fr.esgi.al.funprog.argument.ArgumentParserTrait
import fr.esgi.al.funprog.cli.CmdArgumentValue
//import fr.esgi.al.funprog.argument.output.cmd.CmdOutput

object OutputArgumentParser extends ArgumentParserTrait {
//  override def optionName: String = "cmd"

  //todo set le default argument en fonction de la conf
//  private def defaultArgument: OutputArgument = new CmdOutput

//  def parse(args: Array[String]): Option[Argument] = {
//    Option.empty
//  }

  override def parse(args: Array[String]): List[CmdArgumentValue] = ???
}

