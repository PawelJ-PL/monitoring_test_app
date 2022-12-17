ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

val ciOptionsFilter =
  if (!sys.env.contains("CI")) (options: Seq[String]) => options.filterNot(Set("-Xfatal-warnings")) else (options: Seq[String]) => options

val compilerOptions = scalacOptions ~= ciOptionsFilter.andThen(_ :+ "-Ymacro-annotations")

lazy val root = (project in file("."))
  .settings(
    name := "monitoring_test_app",
    compilerOptions,
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
    libraryDependencies ++= Dependencies.allDeps,
    useJGit
  )
  .enablePlugins(GitVersioning, JavaAppPackaging, DockerPlugin)
