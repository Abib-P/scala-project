package fr.esgi.al.funprog.io.output

import fr.esgi.al.funprog.land.{LandConfiguration, LandResult}
import fr.esgi.al.funprog.land.mower.Mower

trait Output{
  def writeOutput(configuration: LandConfiguration, result: List[Mower]): Unit
  def writeOutput2(configuration: LandResult): Unit
}

