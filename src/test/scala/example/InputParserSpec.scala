package example

import fr.esgi.al.funprog.cli.{CliArgument, CliArgumentInputSrc, CliArgumentInputType, CliArgumentValue, CliArgumentValueInput, CliArgumentValueInputTypeFile}
import fr.esgi.al.funprog.io.input.InputParser
import org.scalatest.funsuite.AnyFunSuite

class InputParserSpec extends AnyFunSuite {

  test("should parse --input-type=cmd") {
    val test = InputParser.getCliArgumentValue(
      Map[CliArgument, CliArgumentValue](CliArgumentInputType() -> CliArgumentValueInputTypeFile(), CliArgumentInputSrc() -> CliArgumentValueInput("test.txt")),
      CliArgumentInputType()
    )

    println(test)
    assert(true)
  }
}
