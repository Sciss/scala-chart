package de.sciss.chart
package exporting

import java.io.Closeable

/**
  * @define os stream to where will be written
  * @define file the output file
  */
private[exporting] trait Exporter extends Any with DocMacros {
  private[exporting] final def managed[R <: Closeable](r: R)(f: R => Unit): Unit =
    try { f(r) } finally { r.close() }
}
