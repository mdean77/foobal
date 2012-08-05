package nl.jqno.foobal

import nl.jqno.foobal.io.DateFactory
import nl.jqno.foobal.io.Url
import nl.jqno.foobal.predictoutcomes.Predicter
import nl.jqno.foobal.updateoutcomes.OutcomesUpdater
import nl.jqno.foobal.predictoutcomes.NilNilPredicter

class Main(
    clock: DateFactory = new DateFactory,
    updater: OutcomesUpdater = new OutcomesUpdater,
    predicter: Predicter = new NilNilPredicter) {
  
  def start(args: Array[String]): String = args match {
    case Array("update", url, file) =>
      updater update (new Url(url), file)
      "OK"
    case Array("predict", homeTeam, outTeam) =>
      val history = List()
      predicter predict (history, homeTeam, outTeam, clock.today) toString
    case _ =>
      Main.HELP_TEXT
  }
}

object Main {
  val HELP_TEXT = {
    val s = """foobal.sh
      |  update <url> <file>
      |  predict "<homeTeam>" "<outTeam>"
      |"""
    s.stripMargin
  }
  
  def main(args: Array[String]) {
    println(new Main().start(args))
  }
}