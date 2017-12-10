package otrebski

import java.io.File

import org.scalatest.{FunSuite, Matchers}

class CounterTest extends FunSuite with Matchers {

  test("testCountNotWhite") {
    Counter.countNotWhite("") shouldBe 0
    Counter.countNotWhite(" ") shouldBe 0
    Counter.countNotWhite("a") shouldBe 1
    Counter.countNotWhite("ab ") shouldBe 2
    Counter.countNotWhite("ab cd ed ") shouldBe 6
    Counter.countNotWhite("ab ()=> ed ") shouldBe 8
  }

  test("testMax single file") {
    Counter.max(new File("src/test/resources/src/a.scala")) shouldBe 17
  }

  test("testMax directory") {
    Counter.max(new File("src/test/resources/src/")) shouldBe 28
  }

}
