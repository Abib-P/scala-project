package fr.esgi.al.funprog.land


import fr.esgi.al.funprog.land.Instructions.Instruction
import fr.esgi.al.funprog.land.mower.Mower

case class LandConfiguration( width: Int, height: Int, mowers: Map[Mower, List[Instruction] ])
