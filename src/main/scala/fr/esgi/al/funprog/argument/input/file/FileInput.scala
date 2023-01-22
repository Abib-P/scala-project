package fr.esgi.al.funprog.argument.input.file

import fr.esgi.al.funprog.argument.input.InputArgument
import fr.esgi.al.funprog.land.LandConfiguration

class FileInput(filePath: String) extends InputArgument {
  def argumentName: String = "file"

  def getFile(): String = filePath

  override def print(): Unit = println(s"Input file path: $filePath")

  override def getMandatoryArguments(): List[InputArgument] = List()

  override def getInput(): LandConfiguration = {
    LandConfiguration()
  }

}
