package fr.esgi.al.funprog.io.input.file

import fr.esgi.al.funprog.io.input.Input

abstract class FileInput(filePath: String) extends Input {

  def getFile: String = filePath

}
