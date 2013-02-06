/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                                               *
 *  Copyright © 2012-2013 Christian Krause                                                       *
 *                                                                                               *
 *  Christian Krause <kizkizzbangbang@googlemail.com>                                            *
 *                                                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                                               *
 *  This file is part of 'scala-chart'.                                                          *
 *                                                                                               *
 *  This project is free software: you can redistribute it and/or modify it under the terms      *
 *  of the GNU Lesser General Public License as published by the Free Software Foundation,       *
 *  either version 3 of the License, or any later version.                                       *
 *                                                                                               *
 *  This project is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;    *
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    *
 *  See the GNU Lesser General Public License for more details.                                  *
 *                                                                                               *
 *  You should have received a copy of the GNU Lesser General Public License along with this     *
 *  project. If not, see <http://www.gnu.org/licenses/>.                                         *
 *                                                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


package scalax.chart
package data

import scala.collection.JavaConversions.seqAsJavaList
import org.jfree.data.general.{ AbstractDataset, DefaultPieDataset }

trait PieDatasetConverter[A] {
  def convert(m: A): PieDataset
}

object PieDatasetConverter {

  implicit def Array2PieDataset[A <% Comparable[A],B <% Number]: PieDatasetConverter[Array[(A,B)]] = new PieDatasetConverter[Array[(A,B)]] {
    override def convert(m: Array[(A,B)]): PieDataset = new AbstractDataset with PieDataset {
      override def getIndex(key: Comparable[_]): Int = m.indexOf(key)
      override def getItemCount(): Int = m.length
      override def getKey(i: Int): Comparable[_] = m(i)._1
      override def getKeys(): java.util.List[A] = m.map(_._1)
      override def getValue(x: Comparable[_]): Number = m.find(_._1 == x).map[Number](_._2).orNull
      override def getValue(i: Int): Number = m(i)._2
    }
  }

  implicit def Vector2PieDataset[A <% Comparable[A],B <% Number]: PieDatasetConverter[Vector[(A,B)]] = new PieDatasetConverter[Vector[(A,B)]] {
    override def convert(m: Vector[(A,B)]): PieDataset = new AbstractDataset with PieDataset {
      override def getIndex(key: Comparable[_]): Int = m.indexOf(key)
      override def getItemCount(): Int = m.length
      override def getKey(i: Int): Comparable[_] = m(i)._1
      override def getKeys(): java.util.List[A] = m.map(_._1)
      override def getValue(x: Comparable[_]): Number = m.find(_._1 == x).map[Number](_._2).orNull
      override def getValue(i: Int): Number = m(i)._2
    }
  }

  // -----------------------------------------------------------------------------------------------
  // direct JFreeChart integration type classes
  // -----------------------------------------------------------------------------------------------

  import org.jfree.data.general.{ DatasetChangeListener, DatasetGroup }

  implicit val DefaultPieDataset2PieDataset: PieDatasetConverter[DefaultPieDataset] = new PieDatasetConverter[DefaultPieDataset] {
    override def convert(m: DefaultPieDataset): PieDataset = new PieDataset {
      override def addChangeListener(l: DatasetChangeListener) { m.addChangeListener(l) }
      override def getGroup(): DatasetGroup = m.getGroup
      override def getIndex(key: Comparable[_]): Int = m.getIndex(key)
      override def getItemCount(): Int = m.getItemCount()
      override def getKey(i: Int): Comparable[_] = m.getKey(i)
      override def getKeys(): java.util.List[_] = m.getKeys()
      override def getValue(x: Comparable[_]): Number = m.getValue(x)
      override def getValue(i: Int): Number = m.getValue(i)
      override def removeChangeListener(l: DatasetChangeListener) { m.removeChangeListener(l) }
      override def setGroup(g: DatasetGroup) { m.setGroup(g) }
    }
  }

}
