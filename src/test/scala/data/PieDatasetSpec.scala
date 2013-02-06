package scalax.chart
package data

import org.specs2._

class PieDatasetSpec extends Specification { def is =

  // -----------------------------------------------------------------------------------------------
  // fragments
  // -----------------------------------------------------------------------------------------------

  "PieDataset Conversion"                                                                          ^
    "Array[(Comparable[_],Number)] to PieDataset"                               ! pdcarray         ^
    "DefaultPieDataset to PieDataset"                                           ! pdcjpiedataset   ^
    "Vector[(Comparable[_],Number)] to PieDataset"                              ! pdcvector        ^
                                                                                                 end
  // -----------------------------------------------------------------------------------------------
  // tests
  // -----------------------------------------------------------------------------------------------

  def pdcarray = pdc(Array.tabulate(2)(kvfunc)) must beAnInstanceOf[PieDataset]
  def pdcjpiedataset = pdc(
    new org.jfree.data.general.DefaultPieDataset()
  ) must beAnInstanceOf[PieDataset]
  def pdcvector = pdc(Vector.tabulate(2)(kvfunc)) must beAnInstanceOf[PieDataset]

  // -----------------------------------------------------------------------------------------------
  // util
  // -----------------------------------------------------------------------------------------------

  def pdc[Data](data: Data)(implicit conv: PieDatasetConverter[Data]) = conv.convert(data)
  def kvfunc = (i: Int) ⇒ i.toString → i

}
