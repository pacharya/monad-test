package org.somewhere.persistence

import org.somewhere.model._

private[persistence] object Movies {

  private def toMapTuple(t: (String, String, String)) : (String, Movie) = {
    val (movieId, movieName, actors) = t
    (movieId, Movie(movieId, movieName, actors.split(",").toList))
  }

  private val allMovies: Map[String, Movie] = List(
    ("M1", "Movie1", "A1,A3,A5"),
    ("M2", "Movie2", "A1,A2,A8"),
    ("M3", "Movie3", "A6,A7,A8"),
    ("M4", "Movie4", "A2,A3,A4,A5,A6,A7"),
    ("M5", "Movie5", "A3,A6,A7"),
    ("M6", "Movie6", "A1,A3,A5"),
    ("M7", "Movie7", "A2,A4,A6"),
    ("M8", "Movie8", "A5")
  ).map(toMapTuple).toMap

  def getActorIds(movieId: String) : List[String] = allMovies.get(movieId).map(_.actorIds).getOrElse(Nil)

}
