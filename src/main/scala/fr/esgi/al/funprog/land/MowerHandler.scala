package fr.esgi.al.funprog.land

import fr.esgi.al.funprog.land.Instructions.Instruction
import fr.esgi.al.funprog.land.mower.Mower

case class MowerHandler(start: Mower, instructions: List[Instruction], end: Mower)
