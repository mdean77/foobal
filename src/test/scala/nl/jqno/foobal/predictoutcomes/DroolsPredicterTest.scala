package nl.jqno.foobal.predictoutcomes

import java.lang.IllegalStateException

import org.drools.runtime.rule.ConsequenceException
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers

import nl.jqno.foobal.io.SampleData._

@RunWith(classOf[JUnitRunner])
class DroolsPredicterTest extends FlatSpec with ShouldMatchers {
  behavior of "A DroolsPredicter"
  
  it should "round-trip through the rule engine" in {
    val p = new DroolsPredicter("drl/fail.drl")
    val ex = evaluating { p.predict(List(), "", "", null) } should produce [ConsequenceException]
    ex.getCause.getClass should be (classOf[IllegalStateException])
  }
  
  it should "generate output based on input" in {
    val result = ValidOutcomes_2(0)
    val p = new DroolsPredicter("drl/nac-finder.drl")
    val out = p.predict(ValidOutcomes_2, result.homeTeam, result.outTeam, result.date);
    out should be (result)
  }
}