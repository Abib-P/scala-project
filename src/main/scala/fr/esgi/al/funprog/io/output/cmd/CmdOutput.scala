package fr.esgi.al.funprog.io.output.cmd

import fr.esgi.al.funprog.io.output.Output
import fr.esgi.al.funprog.land.LandResult
import fr.esgi.al.funprog.land.mower.Orientation

case class CmdOutput() extends Output {
  override def writeOutput(configuration: LandResult): Unit = {
    configuration.mowers.foreach(mower => {
      println(
        mower.end.getPosition.getX.toString + " " + mower.end.getPosition.getY.toString + " " + Orientation
          .toChar(mower.end.getOrientation)
          .toString
      )
    })
  }
}
