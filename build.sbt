name := "Kafka-Scala-Example"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "org.apache.kafka" %% "kafka" % "2.4.0"

addCommandAlias("a",  "runMain EvenCountProducer")
addCommandAlias("b",  "runMain OddCountProducer")
addCommandAlias("c",  "runMain Consumer")
