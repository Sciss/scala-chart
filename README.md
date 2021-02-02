# Scala Chart

[![Build Status](https://github.com/Sciss/scala-chart/workflows/Scala%20CI/badge.svg?branch=main)](https://github.com/Sciss/scala-chart/actions?query=workflow%3A%22Scala+CI%22)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.sciss/scala-chart_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.sciss/scala-chart_2.13)
[![Scaladoc](http://javadoc-badge.appspot.com/de.sciss/scala-chart_2.13.svg?label=scaladoc)](http://javadoc-badge.appspot.com/de.sciss/scala-chart_2.13)

`scala-chart` is a Scala library for creating and working with charts. It wraps [JFreeChart][], much
like `scala-swing` does with the original `javax.swing` package. This project is released under the
same license as JFreeChart, LGPL v3+, to make them fully license-compatible.

This is a fork from [github.com/wookietreiber/scala-chart](https://github.com/wookietreiber/scala-chart), into
my own name space `de.sciss.chart` and publishing to a separate Maven artifact with group-id `de.sciss`.
The original author is Christian Krause.

Below is the original read-me, only adapted to reflect the changes in package names.

----------

Checkout the API by clicking
on the *scaladoc* badge above.

Usage
-----

Add the following to your [sbt][] build:

```scala
libraryDependencies += "de.sciss" %% "scala-chart" % "0.8.0"
```

In case exporting to PDF is required, also add [iText][] to your dependencies:

```scala
libraryDependencies += "com.itextpdf" % "itextpdf" % "5.5.13"
```

In case exporting to SVG is required, also add [JFreeSVG][] to your dependencies:

```scala
libraryDependencies += "org.jfree" % "jfreesvg" % "3.4"
```

### Imports

All high-level convenience can be imported with the *all you can eat* import:

```scala
import de.sciss.chart.api._
```

For more and more *a la carte* imports, have a look at the [module package][modules] for various
selfless traits. There is also a module containing everything the `api` import does which can be
used in applications directly:

```scala
object MyChartApp extends App with de.sciss.chart.module.Charting {
  val data = for (i <- 1 to 5) yield (i,i)
  val chart = XYLineChart(data)
  chart.saveAsPNG("/tmp/chart.png")
}
```

### Creating Charts

Creating charts is as simple as using one of the many chart factories, which differ from the
[JFreeChart][] ones in the aspect, that they make heavy use of default arguments, so you have to
type as less as possible:

```scala
val data = for (i <- 1 to 5) yield (i,i)
val chart = XYLineChart(data)
```

There are also some enrichments for the charts themselves to display them in a window or save them
to disk:

```scala
chart.show(title = "My Chart of Some Points")
```

```scala
chart.saveAsPNG("/tmp/chart.png")
chart.saveAsJPEG("/tmp/chart.jpg")
chart.saveAsPDF("/tmp/chart.pdf")
chart.saveAsSVG("/tmp/chart.svg")
```

### Animations / Live Chart Updates

You can also do some animations, i.e. perform live updates on your datasets:

```scala
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
```


[JFreeChart]: http://jfree.org/jfreechart/
[JFreeSVG]: http://www.jfree.org/jfreesvg/
[sbt]: http://www.scala-sbt.org/
[maven]: http://maven.apache.org/
[modules]: http://wookietreiber.github.io/scala-chart/latest/api/index.html#de.sciss.chart.module.package
[iText]: http://itextpdf.com/
