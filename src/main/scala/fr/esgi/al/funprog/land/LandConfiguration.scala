package fr.esgi.al.funprog.land


import fr.esgi.al.funprog.land.Instructions.Instruction
import fr.esgi.al.funprog.land.mower.{Mower, Position}

case class LandConfiguration( width: Int, height: Int, mowers: Map[Mower, List[Instruction] ]) {

  private def isInside(position: Position): Boolean = {
    position.getX >= 0 && position.getX <= width && position.getY >= 0 && position.getY <= height
  }

  def resolve(): LandResult = {
    LandResult(width, height, mowers.map { case (mower, instructions) => MowerHandler(mower, instructions, instructions.foldLeft(mower)((oldMower, instruction) => {
      val newMower = oldMower.move(instruction)
      if (isInside(newMower.getPosition)) newMower else oldMower
    }))}.toList)
  }
}