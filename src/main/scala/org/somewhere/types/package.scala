package org.somewhere.types

import scalaz._
  import Scalaz._
  import effect._
    import IO._

import org.somewhere.model.Actor

object `package` {

  type ActorCache = Map[String, Actor]
  type ActorCacheMonad[+A] = State[ActorCache, A]

  type ActorCacheMonadT[M[+_], +A] = StateT[M, ActorCache, A]
  type ActorCacheMonadIO[+A] = ActorCacheMonadT[IO, A]

}
