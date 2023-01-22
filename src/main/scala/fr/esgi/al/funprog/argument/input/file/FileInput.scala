package fr.esgi.al.funprog.argument.input.file

import fr.esgi.al.funprog.argument.input.InputArgument

class FileInput extends InputArgument {
  def argumentName: String = "file"

  override def getInput() = {
    println("FileInput")
  }
}
