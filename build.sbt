lazy val projectVersion = "0.7.1"
lazy val mimaVersion    = "0.7.0"
lazy val baseName       = "scala-chart"
lazy val baseNameL      = baseName.toLowerCase

lazy val root = project.withId(baseNameL).in(file("."))
  .settings(
    name                := baseName,
    organization        := "de.sciss",
    version             := projectVersion,
    description         := "Scala Chart Library",
    homepage            := Some(url("https://github.com/wookietreiber/scala-chart")),
    startYear           := Some(2012),
    licenses            := Seq(
      "GNU Lesser General Public Licence" -> url("http://www.gnu.org/licenses/lgpl.txt")
    ),
    apiURL              := Some(url("http://wookietreiber.github.io/scala-chart/latest/api/")),
    scalaVersion        := "2.12.8",
    crossScalaVersions  := Seq("2.12.8", "2.11.12", "2.13.0-RC1"),
    autoAPIMappings := true,
    apiURL := Some(url(s"""http://wookietreiber.github.io/scala-chart/${version.value}/api/""")),
    scalacOptions in Test ++= Seq("-Yrangepos", "-deprecation", "-unchecked", "-feature",
      "-encoding", "utf8", "-Xlint:-stars-align,_", "-Xsource:2.13"),
    libraryDependencies ++= Seq(
      "org.scala-lang.modules"  %%  "scala-swing" % deps.main.scalaSwing,
      "org.jfree"               %   "jfreechart"  % deps.main.jfreechart,
      "org.jfree"               %   "jfreesvg"    % deps.opt.jfreesvg  % Optional,
      "com.itextpdf"            %   "itextpdf"    % deps.opt.itext     % Optional,
      "org.specs2"              %%  "specs2-core" % deps.test.specs2   % Test
    ),
    mimaPreviousArtifacts := Set("de.sciss" %% baseNameL % mimaVersion),
    initialCommands in (Compile, consoleQuick) := (initialCommands in Compile).value,
    initialCommands in Compile in console +=
      """import de.sciss.chart._
        |import de.sciss.chart.api._
        |""".stripMargin
  )
  .settings(publishSettings)

lazy val deps = new {
  val main = new {
    val scalaSwing  = "2.1.1"
    val jfreechart  = "1.0.19"	// N.B. newer versions use crappy java fx
  }
  val opt = new {
    val jfreesvg    = "3.4"
    val itext       = "5.5.13"
  }

  val test = new {
    val specs2      = "4.5.1"
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
    url("https://git.iem.at/sciss/scala-chart"),
    "scm:git:git://git.iem.at/sciss/scala-chart.git",
    Some("scm:git:https://git.iem.at/sciss/scala-chart.git")
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
