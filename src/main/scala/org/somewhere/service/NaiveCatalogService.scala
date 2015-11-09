package org.somewhere.service

import scalaz._
  import Scalaz._
  import effect._
    import IO._

import org.somewhere.model.Actor

class NaiveCatalogService extends CatalogService {

  def getActorsForMovieIds(movieIds: List[String],
                           movieService: MovieService,
                           actorService: ActorService): IO[Map[String, List[Actor]]] = {
    val movieIdAndActors = movieIds traverse { movieId =>
      for {
        actorIds <- movieService.getActorIdsForMovieId(movieId)
        actors <- actorIds traverse { actorId =>
          actorService.getActorById(actorId)
        }
      } yield (movieId, actors.flatten)
    }
    movieIdAndActors map(_.toMap)
  }

}
