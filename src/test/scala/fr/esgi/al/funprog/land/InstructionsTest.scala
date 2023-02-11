package fr.esgi.al.funprog.land

import org.scalatest.funsuite.AnyFunSuite

class InstructionsTest extends AnyFunSuite {
    test("should parse D") {
      assert(Instructions.fromChar('D') == Instructions.D)
    }

    test("should parse G") {
      assert(Instructions.fromChar('G') == Instructions.G)
    }

    test("should parse A") {
      assert(Instructions.fromChar('A') == Instructions.A)
    }
}
