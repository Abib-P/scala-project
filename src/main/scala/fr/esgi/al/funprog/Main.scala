package fr.esgi.al.funprog

import fr.esgi.al.funprog.cli.{CliArgument, CliArgumentInputSrc, CliArgumentInputType, CliArgumentOutputSrc, CliArgumentOutputType}
import fr.esgi.al.funprog.io.input.InputParser
import fr.esgi.al.funprog.io.output.OutputParser
object Main extends App {
  val cliArgs = cli.parseCliArgument(args.toList, List[CliArgument](CliArgumentInputType() , CliArgumentOutputType(), CliArgumentInputSrc(), CliArgumentOutputSrc() ))
  val input = InputParser.parseCliArgument(cliArgs)
  val output = OutputParser.parseCliArgument(cliArgs)

//  val map = Map(
//    new Mower(new Position(1, 2), Orientation.North) -> List(Instructions.G, Instructions.A, Instructions.G, Instructions.A, Instructions.G, Instructions.A, Instructions.G, Instructions.A, Instructions.A),
//    new Mower(new Position(3, 3), Orientation.East) -> List(Instructions.A, Instructions.A, Instructions.D, Instructions.A, Instructions.A, Instructions.D, Instructions.A, Instructions.D, Instructions.D, Instructions.A),
//    new Mower(new Position(0, 5), Orientation.North) -> List(Instructions.A),
//  )
  val land = input.getInput()
  private val res = land.resolve2()

  output.writeOutput2( res)

}

