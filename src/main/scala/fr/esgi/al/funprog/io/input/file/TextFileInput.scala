package fr.esgi.al.funprog.io.input.file

import better.files._
import fr.esgi.al.funprog.io.input.DonneesIncorectesException
import fr.esgi.al.funprog.io.input.InputParser.{
  stringToInstructions,
  stringToLandSize,
  stringToMower
}
import fr.esgi.al.funprog.land.LandConfiguration

case class TextFileInput(name: String) extends FileInput(name) {

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  override def getInput: LandConfiguration = {
    val f = File(getFile)

    f.lines.toList match {
      case ::(head, next) =>
        val (width, height) = stringToLandSize(head)

        val mowers = next
          .grouped(2)
          .map(group => {
            val mower = group.headOption
              .getOrElse(throw DonneesIncorectesException("Line is empty"))
            val instructions = group.lastOption.getOrElse(
              throw DonneesIncorectesException(
                "Line must be mower instructions"
              )
            )
            stringToMower(mower) -> stringToInstructions(instructions)
          })
          .toMap

        LandConfiguration(width, height, mowers)
      case _ => throw DonneesIncorectesException("File is empty")
    }
  }
}
