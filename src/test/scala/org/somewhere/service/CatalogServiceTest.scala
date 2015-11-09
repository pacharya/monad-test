package org.somewhere.service

import org.scalatest.FunSuite

import org.somewhere.model._

class CatalogServiceTest extends FunSuite {

  test("SimpleActorService supports the catalog service") {
    val moviesToActors: Map[String, List[Actor]] =
      SimpleCatalogService.getActorsForMovieIds(
        List("M1","M4","M7"),
        SimpleMovieService,
        SimpleActorService).unsafePerformIO
    assert(moviesToActors.size == 3)
    val m4Actors = moviesToActors.getOrElse("M4",Nil)
    assert(m4Actors.length == 6)
    assert(m4Actors(4).name === "Actor6")
  }

}
