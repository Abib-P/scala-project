package fr.esgi.al.funprog.io.output.cmd

import fr.esgi.al.funprog.io.output.Output
import fr.esgi.al.funprog.land.LandConfiguration
import fr.esgi.al.funprog.land.mower.Mower

class CmdOutput extends Output {
  override def writeOutput(configuration: LandConfiguration, result: List[Mower]): Unit = {
    println("FileOutput.writeOutput")
  }

}
