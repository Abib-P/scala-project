package fr.esgi.al.funprog.io.input.cmd

import fr.esgi.al.funprog.io.input.Input
import fr.esgi.al.funprog.io.input.InputParser.{
  stringToInstructions,
  stringToLandSize,
  stringToMower
}
import fr.esgi.al.funprog.land.LandConfiguration

case class CmdInput() extends Input {

  override def getInput: LandConfiguration = {
    print("Enter land width and heigh (W H):")
    val (width, height) = stringToLandSize(scala.io.StdIn.readLine())

    print("Enter the number of mower (X):")
    val nbMower = scala.io.StdIn.readInt()

    val map = Range
      .inclusive(1, nbMower)
      .map(_ => {
        print("Mower position (X Y O):")
        val mower = stringToMower(scala.io.StdIn.readLine())

        print("Enter mower instructions (AGD...):")
        val instructions = stringToInstructions(scala.io.StdIn.readLine())
        mower -> instructions
      })
      .toList
      .toMap

    LandConfiguration(width, height, map)
  }
}
