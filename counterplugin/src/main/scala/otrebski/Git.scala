package otrebski


/*
 * Copyright 2015 Heiko Seeberger
 * Copyright 2017 Krzysztof Otrebski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File

import org.eclipse.jgit.api.{Git => JGit}
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.storage.file.FileRepositoryBuilder

import scala.annotation.tailrec
import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

object Git {

  def apply(): Git = {

    @tailrec
    def findGitRoot(dir: File, upLevels: Int): File = {
      if (upLevels > 0) {
        val git = new Git((new FileRepositoryBuilder).setWorkTree(dir).build())
        val triedString = git.currentId()
        triedString match {
          case Success(_) => dir
          case Failure(_) if dir.getParentFile != null => findGitRoot(dir.getParentFile, upLevels - 1)
          case Failure(_) => dir
        }
      } else {
        dir
      }
    }

    val gitRoot = findGitRoot(new File("").getAbsoluteFile, 6)
    new Git((new FileRepositoryBuilder).setWorkTree(gitRoot).build())
  }

}

class Git (repository: Repository) {

  private val jgit = new JGit(repository)

  def currentId(): Try[String] =
    Try {
      jgit.log
        .setMaxCount(1)
        .call()
        .asScala
        .map(_.getId.abbreviate(7).name)
        .head
    }

}


