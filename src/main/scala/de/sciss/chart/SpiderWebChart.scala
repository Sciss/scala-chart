package de.sciss.chart

import de.sciss.chart.module.CategoryDatasetConversions._
import org.jfree.ui.RectangleInsets

/** Represents categorized numeric data with a "Spider Web" radar. */
abstract class SpiderWebChart protected () extends Chart {
  type Plot = SpiderWebPlot
  override def plot: SpiderWebPlot = peer.getPlot.asInstanceOf[SpiderWebPlot]
}

/** Factory for ${chart}s.
  *
  * @define chart SpiderWeb chart
  * @define Chart SpiderWebChart
  */
object SpiderWebChart extends ChartCompanion[SpiderWebChart, SpiderWebPlot] {

  override final def fromPeer(jfree: JFreeChart): SpiderWebChart = {
    require(jfree.getPlot.isInstanceOf[Plot], "Illegal peer plot type.")

    new SpiderWebChart {
      override final lazy val peer = jfree
    }
  }

  /** Creates a new $chart.
    *
    * @param data  $data
    * @param theme $theme
    *
    * @usecase def apply(data: CategoryDataset): SpiderWebChart = ???
    */
  def apply[A: ToCategoryDataset](data: A)
    (implicit theme: ChartTheme = ChartTheme.Default): SpiderWebChart = {
    val dataset = ToCategoryDataset[A].convert(data)

    val plot = new SpiderWebPlot(dataset)
    plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0))

    SpiderWebChart(plot, title = "", legend = true)
  }
}
