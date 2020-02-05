package bin

import model.PeriodicCountProducer

object EvenCountProducer extends App {
  new PeriodicCountProducer( // I wanted to do method chaining at first, but who needs that with named parameters?
    topic = "Counting",
    key = "A",
    start = 0,
    increment = 2,
    period = 1000
  ).startSending()
}

object OddCountProducer extends App {
  new PeriodicCountProducer(
    topic = "Counting",
    key = "B",
    start = 1,
    increment = 2,
    period = 2000
  ).startSending()
}
