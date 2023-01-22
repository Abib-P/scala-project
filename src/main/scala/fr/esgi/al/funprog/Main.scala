package fr.esgi.al.funprog

import fr.esgi.al.funprog.land.{Instructions, LandConfiguration}
import fr.esgi.al.funprog.land.mower.{Mower, Orientation, Position}

object Main extends App {

  val map = Map(
    new Mower(new Position(1, 2), Orientation.North) -> List(Instructions.G, Instructions.A, Instructions.G, Instructions.A, Instructions.G, Instructions.A, Instructions.G, Instructions.A, Instructions.A),
    new Mower(new Position(3, 3), Orientation.East) -> List(Instructions.A, Instructions.A, Instructions.D, Instructions.A, Instructions.A, Instructions.D, Instructions.A, Instructions.D, Instructions.D, Instructions.A),
    new Mower(new Position(0, 5), Orientation.North) -> List(Instructions.A),
  )
  val land = LandConfiguration(5, 5, map)

  val res = land.resolve()

  println(res.map(_.print()))

}
