package de.sciss.chart
package exporting

import java.io.{FileOutputStream, OutputStream}
import java.util.Base64

import org.jfree.chart.encoders.EncoderUtil

/** Exports charts to JPEG images.
  *
  * @see [[module.Exporting]]
  */
class JPEGExporter(val chart: Chart) extends AnyVal with Exporter {

  /** Saves the chart as a JPEG image.
    *
    * @param file $file
    *
    * @usecase def saveAsJPEG(file: String): Unit
    *   @inheritdoc
    */
  def saveAsJPEG(file: String, resolution: (Int,Int) = Chart.Default.Resolution): Unit =
    managed(new FileOutputStream(file)) { os => writeAsJPEG(os, resolution) }

  /** Writes the chart as a JPEG image to the output stream.
    *
    * @param os $os
    * @param resolution $resolution
    *
    * @usecase def writeAsJPEG(os: OutputStream): Unit
    *   @inheritdoc
    */
  def writeAsJPEG(os: OutputStream, resolution: (Int,Int) = Chart.Default.Resolution): Unit =
    os.write(encodeAsJPEG(resolution))

  /** Writes the chart to iTerm2 window
    *
    * @param resolution
    *
    * @usecase def writeToTerm():Unit
    *   @inheritdoc
    */
  def writeToTerm(resolution:(Int,Int) = Chart.Default.Resolution): Unit = {
    val base64Jpg = Base64.getEncoder.encodeToString(encodeAsJPEG(resolution))
    print(s"\u001b]1337;File=name=foo.jpg;size=${base64Jpg.length};inline=1:")
    print(base64Jpg)
    println("\u0007")
  }

  /** Returns the chart as a byte encoded JPEG image.
    *
    * @param resolution $resolution
    *
    * @usecase def encodeAsJPEG(): Array[Byte]
    *   @inheritdoc
    */
  def encodeAsJPEG(resolution: (Int, Int) = Chart.Default.Resolution): Array[Byte] = {
    val (width, height) = resolution
    val image = chart.peer.createBufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB, null)
    EncoderUtil.encode(image, "jpeg")
  }

}
