package de.sciss.chart
package exporting

import java.io.{FileOutputStream, OutputStream}

import com.itextpdf.awt.{DefaultFontMapper, FontMapper, PdfGraphics2D}
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.{Document, Rectangle}

/** Exports charts to PDF documents.
  *
  * @see [[module.Exporting]]
  * @define fontMapper handles mappings between Java AWT Fonts and PDF fonts
  */
class PDFExporter(val chart: Chart) extends AnyVal with Exporter {

  /** Returns a new default font mapper. */
  final def DefaultFontMapper: FontMapper =
    new DefaultFontMapper

  /** Saves the chart as a PDF document.
    *
    * @param file       $file
    * @param resolution $resolution
    * @param fontMapper $fontMapper
    *
    * @usecase def saveAsPDF(file: String): Unit
    *   @inheritdoc
    */
  def saveAsPDF(file: String, resolution: (Int,Int) = Chart.Default.Resolution, fontMapper: FontMapper = DefaultFontMapper): Unit =
    managed(new FileOutputStream(file)) { os => writeAsPDF(os, resolution, fontMapper) }

  /** Writes the chart as a PDF document to the output stream.
    *
    * @param os $os
    * @param resolution $resolution
    * @param fontMapper $fontMapper
    *
    * @usecase def writeAsPDF(os: OutputStream): Unit
    *   @inheritdoc
    */
  def writeAsPDF(os: OutputStream, resolution: (Int,Int) = Chart.Default.Resolution, fontMapper: FontMapper = DefaultFontMapper): Unit = {
    val (width,height) = resolution

    val pagesize = new Rectangle(width.toFloat, height.toFloat)
    val document = new Document(pagesize)

    try {
      val writer = PdfWriter.getInstance(document, os)
      document.open()

      val cb = writer.getDirectContent
      val tp = cb.createTemplate(width.toFloat, height.toFloat)
      val g2 = new PdfGraphics2D(tp, width.toFloat, height.toFloat, fontMapper)
      val r2D = new java.awt.geom.Rectangle2D.Double(0, 0, width.toDouble, height.toDouble)

      chart.peer.draw(g2, r2D)
      g2.dispose()
      cb.addTemplate(tp, 0, 0)
    } finally {
      document.close()
    }
  }

}
