package model

import java.util.Properties

object DefaultStringProperties {
  def createProducerProps(url: String): Properties = {
    val props = new Properties()
    props.put("bootstrap.servers", url)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props
  }

  def createConsumerProps(url: String): Properties = {
    val props = new Properties()
    props.put("bootstrap.servers", url)
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("auto.offset.reset", "latest")
    props.put("group.id", "consumer-group")
    props
  }
}
