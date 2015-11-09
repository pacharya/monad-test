package org.somewhere.persistence

import scalaz._
  import Scalaz._
  import effect._
    import IO._

import org.somewhere.model._

object `package` {

 def getActorIdsForMovieId(movieId: String): IO[List[String]] =
   IO {
     Movies.getActorIds(movieId)
   }

 def getActorById(actorId: String): IO[Option[Actor]] =
   IO {
     Actors.getActor(actorId)
   }

}
