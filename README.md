# In addition to the code: an answer to the why
## What is new/different about Kafka Streams?

Apparently, before Kafka Streams, streaming data used to be quite the hassle.

### Issues

You would need a separate pipeline for every consumer, which would explode the complexity of every architecture. Additionally, microbatching was prevalent which didn't reflect real world requirements very well. What about the user, that doesn't have a hundred events per minute? For some having a single event as fast as possible is necessary, imagine the worldwide retail company which needs to update whether a product is sold out.

### Kafka's solutions

Kafka Streams aims to solve these issues by being scalable, real-time and fail-safe. Instead of creating a new pipeline you just register a new topic. If necessary, start up some additional brokers and everything works, no down-time needed. Instead of microbatches it uses real-time data, so that every event gets computed as fast as possible. And since it keeps a log of everything that happens, if one part breaks down the lost data can be reproduced easily.

Another important detail is that Kafka Streams treat tables (mostly) as streams and vice versa. Breaking it down, a table is just a collection of the latest key value pairs of a stream - Kafka utilizes that to generate tables that are not just snapshots, but will be updated whenever necessary. No other framework currently handles tables and streams at the same time like that.  

Sources:
- https://www.confluent.io/blog/introducing-kafka-streams-stream-processing-made-simple/
- https://dzone.com/articles/hands-on-apache-kafka-with-scala
