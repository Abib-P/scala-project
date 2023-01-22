package fr.esgi.al.funprog.argument

import fr.esgi.al.funprog.argument.input.CmdArgumentValue

trait ArgumentParserTrait {
  def parse(args: Array[String]): List[CmdArgumentValue]
}
