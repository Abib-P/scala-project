package fr.esgi.al.funprog.io.input.file

import fr.esgi.al.funprog.io.input.Input
import fr.esgi.al.funprog.land.LandConfiguration

case class FileInput(filePath: String) extends Input {

  def getFile(): String = filePath

  override def getInput(): LandConfiguration = {
    LandConfiguration(0, 0, Map())
  }

}
