package org.somewhere.service

import scalaz._
  import Scalaz._
  import effect._
    import IO._

import org.somewhere.model.Actor

import org.somewhere.types._

trait CatalogService {
  def getActorsForMovieIds(movieIds: List[String],
                           movieService: MovieService,
                           actorService: ActorService): IO[Map[String, List[Actor]]]
}

object SimpleCatalogService extends CatalogService {
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

trait CachingCatalogService {
  def getActorsForMovieIdsWithCache(movieIds: List[String],
                                    movieService: MovieService,
                                    actorService: CachingActorService): IO[Map[String, List[Actor]]]
}

object SimpleCachingCatalogService extends CachingCatalogService {
  def getActorsForMovieIdsWithCache(movieIds: List[String],
                                    movieService: MovieService,
                                    actorService: CachingActorService): IO[Map[String, List[Actor]]] = {

    val movieIdAndActors = movieIds traverseU { movieId =>
      for {
        actorIds <-
          movieService.getActorIdsForMovieId(movieId)
            .liftM[ActorCacheMonadT]
        actors <- actorIds traverseU { actorId =>
          actorService.getActorByIdWithCache(actorId)
        }
      } yield (movieId, actors.flatten)
    }
    val movieIdToActorsMap = movieIdAndActors map { _.toMap }
    movieIdToActorsMap.run( Map.empty[String, Actor]) map(_._2)
  }
}

