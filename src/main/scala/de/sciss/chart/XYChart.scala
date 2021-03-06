package de.sciss.chart

import de.sciss.chart.module.XYLabelGenerators._
import de.sciss.chart.module.XYToolTipGenerators._

import scala.swing.Orientable

/** Represents numeric data. */
abstract class XYChart protected () extends Chart with Orientable
    with Labels[XYLabelGenerator]
    with Tooltips[XYToolTipGenerator] {

  type Plot = XYPlot

  override def plot: XYPlot = peer.getXYPlot

  override def labelGenerator: Option[XYLabelGenerator] = for {
    jgenerator <- Option(plot.getRenderer.getBaseItemLabelGenerator)
    generator = XYLabelGenerator.fromPeer(jgenerator)
  } yield generator

  override def labelGenerator_=(generator: Option[XYLabelGenerator]): Unit = {
    val renderer = plot.getRenderer
    renderer.setBaseItemLabelsVisible(generator.isDefined)
    renderer.setBaseItemLabelGenerator(
      generator.map(XYLabelGenerator.toPeer).orNull
    )
  }

  override def orientation: Orientation = plot.getOrientation
  override def orientation_=(orientation: Orientation): Unit = {
    plot.setOrientation(orientation)
  }

  override def tooltipGenerator: Option[XYToolTipGenerator] = for {
    jgenerator <- Option(plot.getRenderer.getBaseToolTipGenerator)
    generator = XYToolTipGenerator.fromPeer(jgenerator)
  } yield generator

  override def tooltipGenerator_=(generator: Option[XYToolTipGenerator]): Unit = {
    plot.getRenderer.setBaseToolTipGenerator(
      generator.map(XYToolTipGenerator.toPeer).orNull
    )
  }
}

/** Low-level factory for ${chart}s.
  *
  * @define chart XY chart
  * @define Chart XYChart
  */
object XYChart extends ChartCompanion[XYChart, XYPlot] {
  override final def fromPeer(jfree: JFreeChart): XYChart = {
    require(jfree.getPlot.isInstanceOf[Plot], "Illegal peer plot type.")

    new XYChart {
      override final lazy val peer = jfree
    }
  }
}
