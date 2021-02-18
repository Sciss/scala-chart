lazy val projectVersion = "0.8.0"
lazy val mimaVersion    = "0.8.0"
lazy val baseName       = "scala-chart"
lazy val baseNameL      = baseName.toLowerCase

// sonatype plugin requires that these are in global
ThisBuild / version      := projectVersion
ThisBuild / organization := "de.sciss"

lazy val root = project.withId(baseNameL).in(file("."))
  .settings(
    name                := baseName,
//    organization        := "de.sciss",
//    version             := projectVersion,
    description         := "Scala Chart Library",
    homepage            := Some(url("https://github.com/wookietreiber/scala-chart")),
    startYear           := Some(2012),
    licenses            := Seq("LGPL v3+" -> url("http://www.gnu.org/licenses/lgpl-3.0.txt")),
    apiURL              := Some(url("http://wookietreiber.github.io/scala-chart/latest/api/")),
    scalaVersion        := "2.13.4",
    crossScalaVersions  := Seq("3.0.0-RC1", "2.13.4", "2.12.13"),
    autoAPIMappings := true,
    apiURL := Some(url(s"""http://wookietreiber.github.io/scala-chart/${version.value}/api/""")),
    scalacOptions in Test ++= Seq("-Yrangepos", "-deprecation", "-unchecked", "-feature",
      "-encoding", "utf8", "-Xlint:-stars-align,_", "-Xsource:2.13"),
    libraryDependencies ++= Seq(
      "org.scala-lang.modules"  %%  "scala-swing" % deps.main.scalaSwing,
      "org.jfree"               %   "jfreechart"  % deps.main.jfreechart,
      "org.jfree"               %   "jfreesvg"    % deps.opt.jfreesvg  % Optional,
      "com.itextpdf"            %   "itextpdf"    % deps.opt.itext     % Optional
    ),
    libraryDependencies ++= {
      // specs2 currently not available for Dotty
      if (isDotty.value) Nil else Seq(
        "org.specs2" %% "specs2-core" % deps.test.specs2 % Test,
      )
    },
    sources in (Compile, doc) := {
      if (isDotty.value) Nil else (sources in (Compile, doc)).value // dottydoc is broken
    },
    unmanagedSourceDirectories in Test := {
      if (isDotty.value) Nil else (unmanagedSourceDirectories in Test).value 
    },
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
    val scalaSwing  = "3.0.0"
    val jfreechart  = "1.0.19"	// N.B. newer versions use crappy java fx
  }
  val opt = new {
    val jfreesvg    = "3.4"
    val itext       = "5.5.13.2"
  }

  val test = new {
    val specs2      = "4.9.2"
  }
}

lazy val publishSettings = Seq(
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  developers := List(
    Developer(
      id    = "wookietreiber",
      name  = "Christian Krause",
      email = "", // ?
      url   = url("https://github.com/wookietreiber"),
    ),
    Developer(
      id    = "sciss",
      name  = "Hanns Holger Rutz",
      email = "contact@sciss.de",
      url   = url("https://www.sciss.de"),
    ),
  ),
  scmInfo := {
    val h = "git.iem.at"
    val a = s"sciss/$baseName"
    Some(ScmInfo(url(s"https://$h/$a"), s"scm:git@$h:$a.git"))
  },
)

