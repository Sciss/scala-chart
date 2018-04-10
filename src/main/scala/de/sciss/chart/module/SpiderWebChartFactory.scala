package de.sciss.chart
package module

/** $SpiderWebChartFactoryInfo */
object SpiderWebChartFactory extends SpiderWebChartFactory

/** $SpiderWebChartFactoryInfo
  *
  * @define SpiderWebChartFactoryInfo Contains factories to create spider web charts aka radar charts.
  */
trait SpiderWebChartFactory {

  /** Factory for spider web charts. */
  val SpiderWebChart = de.sciss.chart.SpiderWebChart

}
