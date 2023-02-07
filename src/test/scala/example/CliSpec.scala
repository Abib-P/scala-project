package example


import fr.esgi.al.funprog.cli
import fr.esgi.al.funprog.cli.{CmdArgumentInputSrc, CmdArgumentInputType, CmdArgumentValueInput, CmdArgumentValueInputTypeCmd, CmdArgumentValueInvalid}
import org.scalatest.funsuite.AnyFunSuite

class CliSpec extends AnyFunSuite {

  test("should parse --input-type=cmd") {
    cli.parseCmdArgument(List("--input-type=cmd"), List(CmdArgumentInputType()))
      .get(CmdArgumentInputType()) match {
      case None => fail()
      case Some(CmdArgumentValueInputTypeCmd()) => assert(true)
      case Some(_) => fail()
    }
  }

  test("should not parse --input-type=aaa") {
    val test = cli.parseCmdArgument(List("--input-type=aaa"), List(CmdArgumentInputType())).get(CmdArgumentInputType())
    test match {
      case None => fail()
      case Some(CmdArgumentValueInvalid(arg)) => assert(arg == "aaa")
      case Some(_) => fail()
    }
  }

  test("should parse --input-src=test") {
    val parsed = cli.parseCmdArgument(List("--input-src=test"), List(CmdArgumentInputSrc()))
    val arg = parsed.get(CmdArgumentInputSrc())
    arg match {
      case None => fail()
      case Some(CmdArgumentValueInput(src)) => {
        println("src: \"" + src +"\"")
        assert(src == "test")
      }
      case Some(_) => fail()
    }
  }

}
