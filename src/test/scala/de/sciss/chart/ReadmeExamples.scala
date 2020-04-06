package de.sciss.chart

import de.sciss.chart.api._

object ReadmeExamples {
  def main(args: Array[String]): Unit = {
    args.headOption.getOrElse("") match {
      case "1" => MyChartApp.main(args)
      case "2" => ex2()
      case "3" => ex3()
      case _ =>
        println("Must specify an example number: 1, 2, 3")
        sys.exit(1)
    }
  }

  object MyChartApp extends App with de.sciss.chart.module.Charting {
    val data  = for (i <- 1 to 5) yield (i,i)
    val chart = XYLineChart(data)
    chart.saveAsPNG("/tmp/chart.png")
  }

  def ex2(): Unit = {
    val data = for (i <- 1 to 5) yield (i,i)
    val chart = XYLineChart(data)
    chart.show(title = "My Chart of Some Points")
    chart.saveAsPNG ("/tmp/chart.png")
    chart.saveAsJPEG("/tmp/chart.jpg")
    chart.saveAsPDF ("/tmp/chart.pdf")
    chart.saveAsSVG ("/tmp/chart.svg")
  }

  def ex3(): Unit = {
    val series = new XYSeries("f(x) = sin(x)")
    val chart = XYLineChart(series)
    chart.show()
    for (xi <- -40 to 40) {
      swing.Swing onEDT {
        val x = xi * 0.1
        series.add(x, math.sin(x))
      }
      Thread.sleep(50)
    }
  }
}
