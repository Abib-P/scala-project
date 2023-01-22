package fr.esgi.al.funprog.argument.input.cmd

import fr.esgi.al.funprog.argument.input.InputArgument
import fr.esgi.al.funprog.land.LandConfiguration

class CmdInput extends InputArgument {
  def argumentName: String = "cmd"

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  override def getInput(): LandConfiguration = {
    throw new Exception("Not implemented")
  }
}

