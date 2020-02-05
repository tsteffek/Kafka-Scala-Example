package model

import java.util.{Properties, Timer, TimerTask}

import org.apache.kafka.clients.producer._

class PeriodicProducer(delay: Int = 0,
                       period: Int = 1000,
                       props: Properties = DefaultStringProperties.createProducerProps("localhost:9092")
                      ) extends KafkaProducer[String, String](props) {

  def sendPeriodically(iterator: Iterator[ProducerRecord[String, String]]) =
    new Timer() scheduleAtFixedRate(createSelfKillingTimerTask(iterator), delay, period)

  /** Kills itself once the iterator runs out of values
   *
   * @param iterator the iterator producing a tuple of (key, value)
   * @return a [[TimerTask]] to be used by a [[Timer]]
   */
  private def createSelfKillingTimerTask(iterator: Iterator[ProducerRecord[String, String]]): TimerTask =
    new TimerTask {
      def run(): Unit = {
        if (iterator.hasNext)
          send(iterator.next())
        else cancel()
      }
    }
}

class PeriodicCountProducer(
                             topic: String,
                             key: String,
                             start: Int,
                             increment: Int,
                             delay: Int = 0,
                             period: Int = 1000
                           ) extends PeriodicProducer(delay, period) {

  def startSending() =
    sendPeriodically(incrementingRecordsStream(start, increment).iterator)

  /**
   * @param start     the value to start at
   * @param increment the value to increment with
   * @return a [[LazyList]] (aka Stream, for non-Scala-people) of records with increasing values
   */
  private def incrementingRecordsStream(start: Int, increment: Int): LazyList[ProducerRecord[String, String]] =
    new ProducerRecord[String, String](topic, key, start.toString) #:: incrementingRecordsStream(start + increment, increment)

}

