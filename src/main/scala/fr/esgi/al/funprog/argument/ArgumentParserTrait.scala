package fr.esgi.al.funprog.argument

trait ArgumentParserTrait {
  def optionName: String
  def parse(args: Array[String]): Option[Argument]
}
