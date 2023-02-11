package fr.esgi.al.funprog.cli

import fr.esgi.al.funprog.cli
import org.scalatest.funsuite.AnyFunSuite

class CliTest extends AnyFunSuite {

  test("should parse --input-type=cmd") {
    cli.parseCliArgument(List("--input-type=cmd"), List(CliArgumentInputType()))
      .get(CliArgumentInputType()) match {
      case None => fail()
      case Some(CmdArgumentValueInputTypeCli()) => assert(true)
      case Some(_) => fail()
    }
  }

  test("should not parse --input-type=aaa") {
    val test = cli.parseCliArgument(List("--input-type=aaa"), List(CliArgumentInputType())).get(CliArgumentInputType())
    test match {
      case None => fail()
      case Some(CliArgumentValueInvalid(arg)) => assert(arg == "aaa")
      case Some(_) => fail()
    }
  }

  test("should parse --input-src=test") {
    val parsed = cli.parseCliArgument(List("--input-src=test"), List(CliArgumentInputSrc()))
    val arg = parsed.get(CliArgumentInputSrc())
    arg match {
      case None => fail()
      case Some(CliArgumentValueInput(src)) => assert(src == "test")
      case Some(_) => fail()
    }
  }

}
