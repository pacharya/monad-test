package org.somewhere.service

import scalaz._
  import Scalaz._
  import effect._
    import IO._

import org.somewhere.model.Actor
import org.somewhere.persistence

trait ActorService {
  def getActorById(actorId: String): IO[Option[Actor]]
}

object SimpleActorService extends ActorService {
  def getActorById(actorId: String): IO[Option[Actor]] =
    persistence.getActorById(actorId)
}
