package org.somewhere.service

import scalaz._
  import Scalaz._
  import effect._
    import IO._

import org.somewhere.persistence

trait MovieService {
  def getActorIdsForMovieId(movieId: String): IO[List[String]]
}

object SimpleMovieService extends MovieService {
  def getActorIdsForMovieId(movieId: String): IO[List[String]] =
    persistence.getActorIdsForMovieId(movieId)
}
