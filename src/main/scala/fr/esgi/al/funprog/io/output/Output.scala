package fr.esgi.al.funprog.io.output

import fr.esgi.al.funprog.land.LandResult

trait Output{
  def writeOutput(configuration: LandResult): Unit
}
