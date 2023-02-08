package fr.esgi.al.funprog.io.output.cmd

import fr.esgi.al.funprog.io.output.Output
import fr.esgi.al.funprog.land.{LandConfiguration, LandResult}
import fr.esgi.al.funprog.land.mower.{Mower, Orientation}

case class CmdOutput() extends Output {
  override def writeOutput(configuration: LandConfiguration, result: List[Mower]): Unit = {
    result.foreach(mower => {
      println(mower.getPosition.getX.toString + " " + mower.getPosition.getY.toString + " " + Orientation.toChar(mower.getOrientation).toString)
    })
  }

  override def writeOutput2(configuration: LandResult): Unit = {
    configuration.mowers.foreach(mower => {
      println(mower._3.getPosition.getX.toString + " " + mower._3.getPosition.getY.toString + " " + Orientation.toChar(mower._3.getOrientation).toString)
    })
  }
}
