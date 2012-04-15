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

trait BcResult
case class RuntimeFailure(proj: String) extends BcResult
case class Success(proj: String) extends BcResult

object BcTests {
  val SecondCompile = Configuration("second-compile", "The second compilation", true, List(Compile), true)

  val showFirst = TaskKey[Unit]("bc-show-first")
  val showSecond = TaskKey[Unit]("bc-show-second")
  val showDiff = TaskKey[Unit]("bc-show-diff")

  val runBc = TaskKey[BcResult]("bc-run")


  def settings: Seq[Setting[_]] = 
    inConfig(SecondCompile)(Defaults.compileSettings) ++
    Seq(
      scalaSource in SecondCompile <<= sourceDirectory in Compile apply (_ / "scala1"),
      showFirst <<= (sources in Compile) map showSourcesTask,
      showSecond <<= (sources in SecondCompile) map showSourcesTask,
      showDiff <<= (sources in Compile, sources in SecondCompile) map showSourceDiffTask,
      unmanagedClasspath in SecondCompile <<= fullClasspath in Compile,
      mainClass := Some("Main"),
      runBc <<= (name, fullClasspath in SecondCompile, mainClass, runner in run, streams) map runTask
    )


  def runTask(name: String, classpath: Classpath, mainClass: Option[String], runner: ScalaRun, s: TaskStreams): BcResult =
    runner.run(mainClass getOrElse "Main", classpath map (_.data), Seq.empty, s.log) match {
       case Some(msg) => Success(name)
       case None      => RuntimeFailure(name)
    }

  def showSourcesTask(sources: Seq[File]): Unit = 
    for{
      file <- sources
      line <- IO.readLines(file)
    } println(line)


  def showSourceDiffTask(s1: Seq[File], s2: Seq[File]) = {
    val sideLength = 50
    val lines1 = wrapSourceFiles(s1, sideLength)
    val lines2 = wrapSourceFiles(s2, sideLength)
    val padLength = (lines1 map (_.length)).max
    for {
      (l,r) <- lines1.zipAll(lines2, "", "")
      line = l.padTo(padLength, ' ') + " | " + r
    } println(line)
  }

  def wrapSourceFiles(sources: Seq[File], maxLength: Int): Seq[String] =
    for{
      file <- sources
      line <- IO.readLines(file)
      line2 <- splitLine(line, maxLength)
    } yield line2

  def splitLine(line: String, maxLength: Int): Seq[String] =
    if(line.length > maxLength)  (line take (maxLength-1) + '\'') +: splitLine(line drop (maxLength-1), maxLength)
    else Vector(line)
}
