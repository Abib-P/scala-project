package fr.esgi.al.funprog.io.input.cmd


import fr.esgi.al.funprog.io.input.Input
import fr.esgi.al.funprog.land.mower.{Mower, Orientation, Position}
import fr.esgi.al.funprog.land.{Instructions, LandConfiguration}

case class CmdInput() extends Input {

  override def getInput(): LandConfiguration = {
    print("Enter land width and height:")
    val arg = scala.io.StdIn.readLine().split(" ")
    val width = arg(0).toInt
    val height = arg(1).toInt

    print("Enter the number of mower:")
    val nbMower = scala.io.StdIn.readInt()

    val map = Range.inclusive(1, nbMower).map(_ => {
      print("Mower position:")
      val position = scala.io.StdIn.readLine()
      val arg = position.split(" ")
      val x = arg(0).toInt
      val y = arg(1).toInt
      val orientation = Orientation.fromChar(arg(2)(0))
      val mower = Mower(new Position(x, y), orientation)
      print("Enter mower instructions:")
      val instructions = scala.io.StdIn.readLine()
        .map( char => Instructions.fromChar(char))
        .toList
      mower -> instructions
    }).toList.toMap

    LandConfiguration(width, height, map)
  }
}

