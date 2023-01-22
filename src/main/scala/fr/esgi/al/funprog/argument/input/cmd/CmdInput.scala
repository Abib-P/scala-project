package fr.esgi.al.funprog.argument.input.cmd

import fr.esgi.al.funprog.argument.input.{DonneesIncorrectesException, InputArgument}
import fr.esgi.al.funprog.land.LandConfiguration

class CmdInput extends InputArgument {
  def argumentName: String = "cmd"

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  override def getInput(): LandConfiguration = {
    // get arguments from command line
    println("Enter the lawn size (x y):")
    val lawnSize = scala.io.StdIn.readLine().split(" ").map(_.toInt)
    if (lawnSize.length != 2 || lawnSize(0) < 0 || lawnSize(1) < 0) {
      throw DonneesIncorrectesException("Lawn size is incorrect")
    } // todo faire le else
    println("Enter the number of mowers:")
    val lawnNumber = scala.io.StdIn.readLine().toInt
    if (lawnNumber < 0) {
      throw DonneesIncorrectesException("Lawn number is incorrect")
    } // todo faire le else


  }
}

