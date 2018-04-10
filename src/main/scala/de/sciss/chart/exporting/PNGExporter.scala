package de.sciss.chart
package exporting

import java.io.{FileOutputStream, OutputStream}

import org.jfree.chart.encoders.EncoderUtil

/** Exports charts to PNG images.
  *
  * @see [[module.Exporting]]
  */
class PNGExporter(val chart: Chart) extends AnyVal with Exporter {

  /** Saves the chart as a PNG image.
    *
    * @param file $file
    * @param resolution $resolution
    *
    * @usecase def saveAsPNG(file: String): Unit
    *   @inheritdoc
    */
  def saveAsPNG(file: String, resolution: (Int,Int) = Chart.Default.Resolution): Unit =
    managed(new FileOutputStream(file)) { os => writeAsPNG(os, resolution) }

  /** Writes the chart as a PNG image to the output stream.
    *
    * @param os $os
    * @param resolution $resolution
    *
    * @usecase def writeAsPNG(os: OutputStream): Unit
    *   @inheritdoc
    */
  def writeAsPNG(os: OutputStream, resolution: (Int,Int) = Chart.Default.Resolution): Unit =
    os.write(encodeAsPNG(resolution))

  /** Returns the chart as a byte encoded PNG image.
    *
    * @param resolution $resolution
    *
    * @usecase def encodeAsPNG(): Array[Byte]
    *   @inheritdoc
    */
  def encodeAsPNG(resolution: (Int, Int) = Chart.Default.Resolution): Array[Byte] = {
    val (width, height) = resolution
    val image = chart.peer.createBufferedImage(width, height)
    EncoderUtil.encode(image, "png")
  }

}
