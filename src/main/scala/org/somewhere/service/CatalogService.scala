package org.somewhere.service

import scalaz._
  import Scalaz._
  import effect._
    import IO._

import org.somewhere.model.Actor

trait CatalogService {
  def getActorsForMovieIds(movieIds: List[String],
                           movieService: MovieService,
                           actorService: ActorService): IO[Map[String, List[Actor]]]
}
