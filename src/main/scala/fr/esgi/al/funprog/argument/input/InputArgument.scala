package fr.esgi.al.funprog.argument.input

import fr.esgi.al.funprog.argument.Argument
import fr.esgi.al.funprog.land.LandConfiguration

trait InputArgument extends Argument {
  def argumentName: String
  def getInput(): LandConfiguration


  def getMandatoryArguments(): List[InputArgument] = List()
}
