package fr.esgi.al.funprog.io.output.file

import fr.esgi.al.funprog.io.output.Output
import fr.esgi.al.funprog.land.LandConfiguration
import fr.esgi.al.funprog.land.mower.Mower

case class FileOutput(filePath: String) extends Output  {

  def getFile(): String = filePath
  override def writeOutput(configuration: LandConfiguration, result: List[Mower]): Unit = {
    println("FileOutput.writeOutput")
  }
}
