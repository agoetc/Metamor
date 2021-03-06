name := """metamor"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  guice,
  jdbc,
  evolutions,
  "com.h2database" % "h2" % "1.4.197" % Test,
  "org.scalikejdbc" %% "scalikejdbc-test" % "3.3.0" % Test,
  "mysql" % "mysql-connector-java" % "6.0.5",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "org.scalikejdbc" %% "scalikejdbc" % "3.3.0",
  "org.scalikejdbc" %% "scalikejdbc-config" % "3.3.0",
  "org.scalikejdbc" %% "scalikejdbc-play-fixture" % "2.6.0-scalikejdbc-3.3",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.6.0-scalikejdbc-3.3",
  "com.dripower" %% "play-circe" % "2610.0",
  "com.pauldijou" %% "jwt-play" % "0.19.0",
  "com.pauldijou" %% "jwt-core" % "0.19.0",
  "com.auth0" % "jwks-rsa" % "0.6.1",
  "org.scalaz" %% "scalaz-core" % "7.2.27"
)

scalafmtConfig := Some(file(".scalafmt.conf"))
scalafmtOnCompile := true

// テスト時は conf/test.conf を読み込む
javaOptions in Test += "-Dconfig.file=conf/test.conf"
