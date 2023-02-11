package fr.esgi.al.funprog.io.output.file

import fr.esgi.al.funprog.io.output.Output

abstract class FileOutput(filePath: String) extends Output {
  def getFile(): String = filePath
}
