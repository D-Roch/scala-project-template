organization := "com.proinnovate"

name := "template-project"

version := "0.1.0-SNAPSHOT"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xcheckinit", "-Xmigration", "-encoding", "UTF-8")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.6.1" % "test"
)


// ls-sbt configuration
// --------------------

seq(lsSettings: _*)

(LsKeys.tags in LsKeys.lsync) := Seq("scala", "project", "template")

(externalResolvers in LsKeys.lsync) := Seq(
  "my own resolver" at "http://oss.sonatype.org")

(description in LsKeys.lsync) :=
  "A project template - use this for your project, don't publish me!"


// sbt-release configuration
// -------------------------

seq(sbtrelease.Release.releaseSettings: _*)

// Maven publishing configuration
// ------------------------------

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { x => false }

pomExtra := (
  <url>http://github.com/sroebuck/scala-project-template</url>
  <licenses>
    <license>
      <name>MIT License</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:sroebuck/scala-project-template.git</url>
    <connection>scm:git:git@github.com:sroebuck/scala-project-template.git</connection>
  </scm>
  <developers>
    <developer>
      <id>sroebuck</id>
      <name>Stuart Roebuck</name>
      <url>https://github.com/sroebuck</url>
    </developer>
  </developers>)
