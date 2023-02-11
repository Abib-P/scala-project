package fr.esgi.al.funprog.io.output

import fr.esgi.al.funprog.cli.{CliArgument, CliArgumentOutputSrc, CliArgumentOutputType, CliArgumentValue, CliArgumentValueInput, CliArgumentValueOutputTypeCsv, CliArgumentValueOutputTypeJson, CliArgumentValueOutputTypeYaml, CmdArgumentValueOutputTypeCli}
import fr.esgi.al.funprog.io.output.cmd.CmdOutput
import fr.esgi.al.funprog.io.output.file.{CsvFileOutput, JsonFileOutput, YamlFileOutput}
import org.scalatest.funsuite.AnyFunSuite

class OutputParserTest extends AnyFunSuite {
  test("default output should be CmdOutput") {
    assert(OutputParser.parseCliArgument(Map()) == CmdOutput())
  }

  test("should parse cli argument") {
    OutputParser.parseCliArgument(Map[CliArgument, CliArgumentValue](CliArgumentOutputType() -> CmdArgumentValueOutputTypeCli())) match {
      case x: CmdOutput => assert(x == CmdOutput())
      case _ => fail()
    }
  }

  test("should parse yaml file output") {
    OutputParser.parseCliArgument(Map[CliArgument, CliArgumentValue](CliArgumentOutputType() -> CliArgumentValueOutputTypeYaml(), CliArgumentOutputSrc() -> CliArgumentValueInput("test"))) match {
      case x: YamlFileOutput => assert(x == YamlFileOutput("test"))
      case _ => fail()
    }
  }

  test("should parse json file output") {
    OutputParser.parseCliArgument(Map[CliArgument, CliArgumentValue](CliArgumentOutputType() -> CliArgumentValueOutputTypeJson(), CliArgumentOutputSrc() -> CliArgumentValueInput("test"))) match {
      case x: JsonFileOutput => assert(x == JsonFileOutput("test"))
      case _ => fail()
    }
  }

  test("should parse csv file output") {
    OutputParser.parseCliArgument(Map[CliArgument, CliArgumentValue](CliArgumentOutputType() -> CliArgumentValueOutputTypeCsv(), CliArgumentOutputSrc() -> CliArgumentValueInput("test"))) match {
      case x: CsvFileOutput => assert(x == CsvFileOutput("test"))
      case _ => fail()
    }
  }
}
