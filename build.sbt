version := "0.1.0"

val appName = "basement-users"

val akkaVersion = "2.5.9"
val akkaHttpVersion = "10.1.0-RC2"

val appDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.4" % Test,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test
)

lazy val microservice = Project(appName, file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    scalaVersion := "2.12.4",
    libraryDependencies ++= appDependencies ++ testDependencies
  )
  // Docker settings
  .settings(
  dockerRepository := Some("wjlea93"),
  dockerBaseImage := "openjdk:8-jre-slim",
  dockerExposedPorts := Seq(9000),
  dockerUpdateLatest := true
)
        