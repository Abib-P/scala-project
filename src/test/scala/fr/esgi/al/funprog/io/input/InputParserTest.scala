package fr.esgi.al.funprog.io.input

import fr.esgi.al.funprog.cli._
import fr.esgi.al.funprog.io.input.cmd.CmdInput
import fr.esgi.al.funprog.io.input.file.TextFileInput
import org.scalatest.funsuite.AnyFunSuite

class InputParserTest extends AnyFunSuite {

  test("should parse --input-type=cmd") {
    val test = InputParser.parseCliArgument(
      Map[CliArgument, CliArgumentValue](CliArgumentInputType() -> CliArgumentValueInputTypeFile(), CliArgumentInputSrc() -> CliArgumentValueInput("test.txt")),
    )

    test match {
      case TextFileInput(filePath) => assert(filePath == "test.txt")
      case _ => fail()
    }
  }

  test("should parse file input with other arguments") {
    val test = InputParser.parseCliArgument(
      Map[CliArgument, CliArgumentValue](CliArgumentInputSrc() -> CliArgumentValueInput("in.txt"), CliArgumentInputType() -> CliArgumentValueInputTypeFile(), CliArgumentOutputSrc() -> CliArgumentValueInput("out.txt"), CliArgumentOutputType() -> CliArgumentValueOutputTypeJson()),
    )

    test match {
      case TextFileInput(filePath) => assert(filePath == "in.txt")
      case _ => fail()
    }
  }

  test("should parse cmd input") {
    val test = InputParser.parseCliArgument(
      Map[CliArgument, CliArgumentValue](CliArgumentInputType() -> CmdArgumentValueInputTypeCli()),
    )

    test match {
      case CmdInput() => assert(true)
      case _ => fail()
    }
  }

  test("should throw exception when no input type is provided") {
    assertThrows[DonneesIncorectesException] {
      InputParser.parseCliArgument(
        Map[CliArgument, CliArgumentValue](),
      )
    }
  }
}
