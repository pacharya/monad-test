package org.somewhere.types

import scalaz._

import org.somewhere.model.Actor

object `package` {

  type ActorCache = Map[String, Actor]
  type ActorCacheMonad[+A] = State[ActorCache, A]

}
