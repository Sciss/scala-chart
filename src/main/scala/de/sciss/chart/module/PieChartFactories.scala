package de.sciss.chart
package module

/** $PieChartFactoriesInfo */
object PieChartFactories extends PieChartFactories

/** $PieChartFactoriesInfo
  *
  * @define PieChartFactoriesInfo Contains factories to create pie charts and other pie-like chart.
  */
trait PieChartFactories {

  /** Factory for multiple pie charts. */
  val MultiplePieChart = de.sciss.chart.MultiplePieChart

  /** Factory for pie charts. */
  val PieChart = de.sciss.chart.PieChart

  /** Factory for ring charts. */
  val RingChart = de.sciss.chart.RingChart

}
