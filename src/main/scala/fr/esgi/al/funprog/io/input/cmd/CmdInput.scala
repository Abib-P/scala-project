package fr.esgi.al.funprog.io.input.cmd

import fr.esgi.al.funprog.io.input.Input
import fr.esgi.al.funprog.land.LandConfiguration

case class CmdInput() extends Input {

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  override def getInput(): LandConfiguration = {


    LandConfiguration(0, 0, Map())
  }
}

