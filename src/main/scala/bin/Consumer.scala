package bin

import java.time.Duration

import model.DefaultStringProperties
import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConverters._

object Consumer extends App {
  val props = DefaultStringProperties.createConsumerProps("localhost:9092")
  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
  val topics = "Counting" :: Nil

  consumer.subscribe(topics.asJava)
  while (true) {
    val record = consumer.poll(Duration.ofMillis(1000)).asScala
    for (data <- record.iterator)
      println(s"${data.key()}: ${data.value()}")
  }
}
