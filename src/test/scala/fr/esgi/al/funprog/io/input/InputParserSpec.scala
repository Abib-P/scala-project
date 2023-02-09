package fr.esgi.al.funprog.io.input

import fr.esgi.al.funprog.cli._
import fr.esgi.al.funprog.io.input.file.TextFileInput
import org.scalatest.funsuite.AnyFunSuite

class InputParserSpec extends AnyFunSuite {

  test("should parse --input-type=cmd") {
    val test = InputParser.parseCliArgument(
      Map[CliArgument, CliArgumentValue](CliArgumentInputType() -> CliArgumentValueInputTypeFile(), CliArgumentInputSrc() -> CliArgumentValueInput("test.txt")),
    )

    test match {
      case TextFileInput(filePath) => assert(filePath == "test.txt")
      case _ => fail()
    }
  }

  test("should parse -") {
    val test = InputParser.parseCliArgument(
      Map[CliArgument, CliArgumentValue](CliArgumentInputSrc() -> CliArgumentValueInput("in.txt"), CliArgumentInputType() -> CliArgumentValueInputTypeFile(), CliArgumentOutputSrc() -> CliArgumentValueInput("out.txt"), CliArgumentOutputType() -> CliArgumentValueOutputTypeFile()),
    )

    test match {
      case TextFileInput(filePath) => assert(filePath == "in.txt")
      case _ => fail()
    }
  }
}
