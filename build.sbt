name := "monad-test"

organization := "org.pacharya"

scalaVersion := "2.10.4"

libraryDependencies ++= {
  Seq(
    "org.scalaz"                         %% "scalaz-core"          % "7.0.6",
    "org.scalaz"                         %% "scalaz-effect"        % "7.0.6",
    "org.scalatest"                      %% "scalatest"            % "2.2.2" % "test"
  )
}

//Don't decorate artifacts with Scala version info
crossPaths := false

fork := true

scalacOptions ++= Seq(
    "-feature"
  , "-deprecation"
  , "-language:higherKinds"
  , "-language:implicitConversions"
)

