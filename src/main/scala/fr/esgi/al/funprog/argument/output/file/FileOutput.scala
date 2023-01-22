package fr.esgi.al.funprog.argument.output.file

import fr.esgi.al.funprog.argument.output.OutputArgument

class FileOutput(filePath: String) extends OutputArgument  {
  override def argumentName: String = ???

  override def print(): Unit = println(" FileOutput.print" + filePath);
}
