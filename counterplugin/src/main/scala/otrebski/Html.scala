package otrebski


import scalatags.Text.all._

object Html {

  def format(projectName:String, max: Int, commitHash: Option[String]): String = {
    val str: String = commitHash.getOrElse("This is not a git project")
    html(
      body(
        h1(s"Counter plugin report for project $projectName"),
        h2(s"Max non-white chars in line: $max"),
        hr(),
        h3(code(str))
      )
    ).toString()
  }
}
