import sbt._
import Keys._


object BcBuild extends Build {

  def defaultSettings: Seq[Setting[_]] = BcTests.settings

  /** Add all sub projects to this project. */
  override def projects = root +: subprojects

  val root = Project("root", file("."))

  /** Detect all binary compatibility projects. */
  lazy val subprojects = 
    for {
      file <- IO.listFiles(file(".")) 
      if (file / "src" / "main" / "scala").exists
      if (file / "src" / "main" / "scala1").exists
    } yield Project(file.getName, file) settings(defaultSettings:_*)

  
}

object BcTests {
  val SecondCompile = Configuration("second-compile", "The second compilation", true, List(Compile), true)

  val showFirst = TaskKey[Unit]("bc-show-first")
  val showSecond = TaskKey[Unit]("bc-show-second")

  val runBc = InputKey[Unit]("bc-run")


  def settings: Seq[Setting[_]] = 
    inConfig(SecondCompile)(Defaults.compileSettings) ++
    Seq(
      scalaSource in SecondCompile <<= sourceDirectory in Compile apply (_ / "scala1"),
      showFirst <<= (sources in Compile) map showSourcesTask,
      showSecond <<= (sources in SecondCompile) map showSourcesTask,
      unmanagedClasspath in SecondCompile <<= fullClasspath in Compile,
      mainClass := Some("Main"),
      runBc <<= Defaults.runTask(fullClasspath in SecondCompile, mainClass, runner in run)
    )


  def showSourcesTask(sources: Seq[File]): Unit = 
    for{
      file <- sources
      line <- IO.readLines(file)
    } println(line)
}
