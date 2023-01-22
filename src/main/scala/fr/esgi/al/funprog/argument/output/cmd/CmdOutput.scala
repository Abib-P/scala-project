package fr.esgi.al.funprog.argument.output.cmd

import fr.esgi.al.funprog.argument.output.OutputArgument

class CmdOutput extends OutputArgument {
  def argumentName: String = "cmd"

  def parse(): Unit = {
    println("CmdOutput.parse")
  }

  override def apply(): Unit = {
    println("CmdOutput.parse")
  }
}
