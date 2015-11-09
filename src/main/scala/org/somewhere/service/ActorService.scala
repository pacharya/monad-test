package org.somewhere.service

import scalaz._
  import Scalaz._
  import effect._
    import IO._

import org.somewhere.model.Actor
import org.somewhere.persistence
import org.somewhere.types._

trait ActorService {
  def getActorById(actorId: String): IO[Option[Actor]]
}

object SimpleActorService extends ActorService {
  def getActorById(actorId: String): IO[Option[Actor]] = {
    println(s"Getting actor ID $actorId")
    persistence.getActorById(actorId)
  }
}

trait CachingActorService {
  def getActorById(actorId: String): IO[Option[Actor]]
  def getActorByIdWithCache(actorId: String): ActorCacheMonadIO[Option[Actor]]
}

object SimpleCachingActorService extends CachingActorService {

  def getActorById(actorId: String): IO[Option[Actor]] = {
    println(s"Getting actor ID $actorId")
    persistence.getActorById(actorId)
  }

  def getActorByIdWithCache(actorId: String): ActorCacheMonadIO[Option[Actor]] = {
    for {
      maybeCachedActor <- (gets { cache: ActorCache => cache.get(actorId)}).lift[IO]
      maybeActor <- maybeCachedActor match {
        case Some(cachedActor) =>
          cachedActor.some.point[ActorCacheMonadIO]
        case None => loadActorInCache(actorId)
      }
    } yield {
      maybeActor
    }
  }

  private def loadActorInCache(actorId: String) : ActorCacheMonadIO[Option[Actor]] = {
    for {
      maybeActor <-
      getActorById(actorId).liftM[ActorCacheMonadT]
      _ <- maybeActor match {
        case Some(actor) => (
          modify { cache: ActorCache =>
            cache + (actorId -> actor)
          }
          ).lift[IO]
        case None => ().point[ActorCacheMonadIO]
      }
    } yield maybeActor
  }

}
