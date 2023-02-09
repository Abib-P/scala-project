package fr.esgi.al.funprog

import fr.esgi.al.funprog.cli.{CliArgument, CliArgumentInputSrc, CliArgumentInputType, CliArgumentOutputSrc, CliArgumentOutputType}
import fr.esgi.al.funprog.io.input.InputParser
import fr.esgi.al.funprog.io.output.OutputParser
object Main extends App {
  val cliArgs = cli.parseCliArgument(args.toList, List[CliArgument](CliArgumentInputType() , CliArgumentOutputType(), CliArgumentInputSrc(), CliArgumentOutputSrc() ))
  val input = InputParser.parseCliArgument(cliArgs)
  val output = OutputParser.parseCliArgument(cliArgs)

  val land = input.getInput
  private val res = land.resolve()

  output.writeOutput( res)

}

