package de.sciss.chart
package exporting

import java.io._

import org.jfree.graphics2d.svg._

/** Exports charts to SVG images.
  *
  * @see [[module.Exporting]]
  */
class SVGExporter(val chart: Chart) extends AnyVal with Exporter {

  /** Saves the chart as a SVG image.
    *
    * @param file $file
    * @param resolution $resolution
    *
    * @usecase def saveAsSVG(file: String): Unit
    *   @inheritdoc
    */
  def saveAsSVG(file: String, resolution: (Int,Int) = Chart.Default.Resolution): Unit = {
    val (width, height) = resolution
    val g2 = new SVGGraphics2D(width, height)
    chart.peer.draw(g2, new java.awt.Rectangle(new java.awt.Dimension(width, height)))
    val svg = g2.getSVGElement
    g2.dispose()
    SVGUtils.writeToSVG(new File(file), svg)
  }

}
