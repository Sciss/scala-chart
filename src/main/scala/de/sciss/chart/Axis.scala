package de.sciss.chart

import de.sciss.chart.module.Imports._

/** Represents a line of reference.
  *
  * @define axis axis
  * @define Axis Axis
  */
abstract class Axis protected () {

  /** Returns the peer $axis type. */
  type Peer <: org.jfree.chart.axis.Axis

  /** Returns the underlying $axis. */
  def peer: Peer

  /** Represents the label of an $axis. */
  final class Label private[chart] () {

    /** Returns the text of this label. */
    def text: String =
      Option(peer.getLabel).getOrElse("")

    /** Sets the text of this label. */
    def text_=(s: String): Unit =
      peer.setLabel(s)

    /** Returns the angle of this label in radians. */
    def angle: Double =
      peer.getLabelAngle

    /** Sets the angle of this label in radians. */
    def angle_=(a: Double): Unit =
      peer.setLabelAngle(a)

    /** Returns the font of this label. */
    def font: Font =
      peer.getLabelFont

    /** Sets the font of this label. */
    def font_=(font: Font): Unit =
      peer.setLabelFont(font)

    /** Returns the insets (surrounding blank space) of this label. */
    def insets: RectangleInsets =
      peer.getLabelInsets

    /** Sets the insets (surrounding blank space) of this label. */
    def insets_=(insets: RectangleInsets): Unit =
      peer.setLabelInsets(insets)

    /** Returns the location of this label. */
    def location: AxisLabelLocation =
      peer.getLabelLocation

    /** Sets the location of this label. */
    def location_=(location: AxisLabelLocation): Unit =
      peer.setLabelLocation(location)

    /** Returns the paint used to draw this label. */
    def paint: Paint =
      peer.getLabelPaint

    /** Sets the paint used to draw this label. */
    def paint_=(paint: Paint): Unit =
      peer.setLabelPaint(paint)

  }

  /** Returns the label of this $axis. */
  final val label: Label = new Label()

  /** Represents the line of an $axis. */
  final class Line private[chart] () {

    /** Returns the paint used to draw this line. */
    def paint: Paint =
      peer.getAxisLinePaint

    /** Sets the paint used to draw this line. */
    def paint_=(paint: Paint): Unit =
      peer.setAxisLinePaint(paint)

    /** Returns the stroke used to draw this line. */
    def stroke: Stroke =
      peer.getAxisLineStroke

    /** Sets the stroke used to draw this line. */
    def stroke_=(stroke: Stroke): Unit =
      peer.setAxisLineStroke(stroke)

    /** Returns true if this $axis line is visible. */
    def visible: Boolean =
      peer.isAxisLineVisible

    /** Sets whether this $axis line is visible. */
    def visible_=(b: Boolean): Unit =
      peer.setAxisLineVisible(b)

  }

  /** Returns the line of this $axis. */
  final val line: Line = new Line()

  /** Represents the tick marks of an $axis. */
  final class Ticks private[chart] () {

    /** Represents the labels of tick marks of an $axis. */
    final class Label private[chart] () {

      /** Returns the font of these tick labels. */
      def font: Font =
        peer.getTickLabelFont

      /** Sets the font of these tick labels. */
      def font_=(font: Font): Unit =
        peer.setTickLabelFont(font)

      /** Returns the insets (surrounding blank space) of these tick labels. */
      def insets: RectangleInsets =
        peer.getTickLabelInsets

      /** Sets the insets (surrounding blank space) of these tick labels. */
      def insets_=(insets: RectangleInsets): Unit =
        peer.setTickLabelInsets(insets)

      /** Returns the paint used to draw these tick labels. */
      def paint: Paint =
        peer.getTickLabelPaint

      /** Sets the paint used to draw these tick labels. */
      def paint_=(paint: Paint): Unit =
        peer.setTickLabelPaint(paint)

      /** Returns true if these tick labels are visible. */
      def visible: Boolean =
        peer.isTickLabelsVisible

      /** Sets whether these tick labels are visible. */
      def visible_=(b: Boolean): Unit =
        peer.setTickLabelsVisible(b)

    }

    /** Returns the tick mark labels of this $axis. */
    val label: Label = new Label()

    /** Represents the minor ticks of an $axis. */
    final class Minor private[chart] () {

      /** Returns the inside length of these minor tick marks. */
      def insideLength: Float =
        peer.getMinorTickMarkInsideLength

      /** Sets the inside length of these minor tick marks. */
      def insideLength_=(length: Float): Unit =
        peer.setMinorTickMarkInsideLength(length)

      /** Returns the outside length of these minor tick marks. */
      def outsideLength: Float =
        peer.getMinorTickMarkOutsideLength

      /** Sets the outside length of these minor tick marks. */
      def outsideLength_=(length: Float): Unit =
        peer.setMinorTickMarkOutsideLength(length)

      /** Returns true if these minor tick lines are visible. */
      def visible: Boolean =
        peer.isTickLabelsVisible

      /** Sets whether these minor tick lines are visible. */
      def visible_=(b: Boolean): Unit =
        peer.setTickLabelsVisible(b)

    }

    /** Returns the minor tick marks of this $axis. */
    val minor: Minor = new Minor()

    /** Returns the paint used to draw these tick marks. */
    def paint: Paint =
      peer.getTickMarkPaint

    /** Sets the paint used to draw these tick marks. */
    def paint_=(paint: Paint): Unit =
      peer.setTickMarkPaint(paint)

    /** Returns the stroke used to draw these tick marks. */
    def stroke: Stroke =
      peer.getTickMarkStroke

    /** Sets the stroke used to draw these tick marks. */
    def stroke_=(stroke: Stroke): Unit =
      peer.setTickMarkStroke(stroke)

  }

  /** Returns the tick marks of this $axis. */
  final val ticks: Ticks = new Ticks()

  /** Returns true if this $axis is visible. */
  final def visible: Boolean =
    peer.isVisible

  /** Sets whether this $axis is visible. */
  final def visible_=(b: Boolean): Unit =
    peer.setVisible(b)

}
