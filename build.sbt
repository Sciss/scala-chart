lazy val root = project.withId("scala-chart").in(file("."))
  .settings(
    name                := "scala-chart",
    organization        := "de.sciss",
    version             := "0.6.0",
    description         := "Scala Chart Library",
    homepage            := Some(url("https://github.com/wookietreiber/scala-chart")),
    startYear           := Some(2012),
    licenses            := Seq(
      "GNU Lesser General Public Licence" -> url("http://www.gnu.org/licenses/lgpl.txt")
    ),
    apiURL              := Some(url("http://wookietreiber.github.io/scala-chart/latest/api/")),
    scalaVersion        := "2.12.5",
    crossScalaVersions  := Seq("2.12.5", "2.11.12"),
    autoAPIMappings := true,
    apiURL := Some(url(s"""http://wookietreiber.github.io/scala-chart/${version.value}/api/""")),
    scalacOptions in Test ++= Seq("-Yrangepos", "-deprecation", "-unchecked", "-feature",
      "-encoding", "utf8", "-Xfuture", "-Xlint:-stars-align,_"),
    libraryDependencies ++= Seq(
      "org.scala-lang.modules"  %%  "scala-swing" % deps.main.scalaSwing,
      "org.jfree"               %   "jfreechart"  % deps.main.jfreechart,
      "org.jfree"               %   "jfreesvg"    % deps.opt.jfreesvg  % Optional,
      "com.itextpdf"            %   "itextpdf"    % deps.opt.itext     % Optional,
      "org.specs2"              %%  "specs2-core" % deps.test.specs2   % Test
    ),
    initialCommands in (Compile, consoleQuick) := (initialCommands in Compile).value,
    initialCommands in Compile in console +=
      """import de.sciss.chart._
        |import de.sciss.chart.api._
        |""".stripMargin
  )
  .settings(publishSettings)

lazy val deps = new {
  val main = new {
    val scalaSwing  = "2.0.3"
    val jfreechart  = "1.0.19"	// N.B. newer versions use crappy java fx
  }
  val opt = new {
    val jfreesvg    = "3.3"
    val itext       = "5.5.13"
  }

  val test = new {
    val specs2      = "4.0.3"
  }
}

lazy val publishSettings = Seq(
  publishMavenStyle := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  scmInfo := Some(ScmInfo(
    url("https://github.com/Sciss/scala-chart"),
    "scm:git:git://github.com/Sciss/scala-chart.git",
    Some("scm:git:https://github.com/Sciss/scala-chart.git")
  )),
  pomExtra := {
    <developers>
      <developer>
        <id>wookietreiber</id>
        <name>Christian Krause</name>
        <url>https://github.com/wookietreiber</url>
      </developer>
      <developer>
        <id>sciss</id>
        <name>Hanns Holger Rutz</name>
        <url>http://www.sciss.de</url>
      </developer>
    </developers>
  }
)
