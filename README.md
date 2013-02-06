Scala Chart Library
===================

`scala-chart` is a Scala library for creating and working with charts. It wraps [JFreeChart][], much
like `scala-swing` does with the original `javax.swing` package. This project is released under the
same license as [JFreeChart][] to make them fully license-compatible. Checkout the [API][].

Usage
-----

### [sbt][]

    libraryDependencies += "com.github.wookietreiber" %% "scala-chart" % "latest.integration"

### [maven][]

    <dependency>
       <groupId>com.github.wookietreiber</groupId>
       <artifactId>scala-chart_${scala.version}</artifactId>
       <version>latest.integration</version>
    </dependency>

Examples
--------

You can import nearly all of the `scala-chart` functionality with the following lines:

    import scalax.chart._
    import scalax.chart.Charting._

You can use the conversions to convert from ordinary Scala collections to the datasets used by
[JFreeChart][]:

    val dataset = Seq((1,2),(2,4),(3,6),(4,8)).toXYSeriesCollection("some points")

These datasets can be used by the chart factories, which differ from the [JFreeChart][] ones in the
aspect, that they make heavy use of default arguments, so you have to type as less as possible:

    val chart = XYLineChart(dataset, title = "My Chart of Some Points")

There are also some enrichments for the charts themselves to display them in a window or save them
to disk:

    chart.show
    chart.saveAsPNG(new java.io.File("/tmp/chart.png"), (1024,768))
    chart.saveAsJPEG(new java.io.File("/tmp/chart.jpg"), (1024,768))
    chart.saveAsPDF(new java.io.File("/tmp/chart.pdf"), (1024,768))


[JFreeChart]: http://jfree.org/jfreechart/
[API]: http://wookietreiber.github.com/scala-chart/latest/api/index.html
[sbt]: http://www.scala-sbt.org/
[maven]: http://maven.apache.org/


---

[![endorse](http://api.coderwall.com/wookietreiber/endorsecount.png)](http://coderwall.com/wookietreiber)

