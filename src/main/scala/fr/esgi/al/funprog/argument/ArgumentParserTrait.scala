package fr.esgi.al.funprog.argument

import fr.esgi.al.funprog.cli.CliArgumentValue

trait ArgumentParserTrait {
  def parse(args: Array[String]): List[CliArgumentValue]
}
