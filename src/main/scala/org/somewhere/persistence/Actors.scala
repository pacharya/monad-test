package org.somewhere.persistence

import org.somewhere.model._

private[persistence] object Actors {

  private def toMapTuple(t: (String, String)) : (String, Actor) = (t._1, Actor(t._1, t._2))

  private val allActors: Map[String, Actor] = List(
    ("A1", "Actor1"),
    ("A2", "Actor2"),
    ("A3", "Actor3"),
    ("A4", "Actor4"),
    ("A5", "Actor5"),
    ("A6", "Actor6"),
    ("A7", "Actor7"),
    ("A8", "Actor8")
  ).map(toMapTuple).toMap

  def getActor(actorId: String) : Option[Actor] = allActors.get(actorId)

}
