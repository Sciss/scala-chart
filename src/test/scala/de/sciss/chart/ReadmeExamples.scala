package de.sciss.chart

import de.sciss.chart.api._

class ReadmeExamples {

  object MyChartApp extends App with de.sciss.chart.module.Charting {
    val data = for (i <- 1 to 5) yield (i,i)
    val chart = XYLineChart(data)
    chart.saveAsPNG("/tmp/chart.png")
  }

  {
    val data = for (i <- 1 to 5) yield (i,i)
    val chart = XYLineChart(data)
    chart.show(title = "My Chart of Some Points")
    chart.saveAsPNG("/tmp/chart.png")
    chart.saveAsJPEG("/tmp/chart.jpg")
    chart.saveAsPDF("/tmp/chart.pdf")
    chart.saveAsSVG("/tmp/chart.svg")
  }

  {
    val series = new XYSeries("f(x) = sin(x)")
    val chart = XYLineChart(series)
    chart.show()
    for (x <- -4.0 to 4 by 0.1) {
      swing.Swing onEDT {
        series.add(x,math.sin(x))
      }
      Thread.sleep(50)
    }
  }
}
