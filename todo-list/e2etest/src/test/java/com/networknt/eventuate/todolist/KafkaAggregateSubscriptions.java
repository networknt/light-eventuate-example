package com.networknt.eventuate.todolist;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.eventuate.cdccore.AggregateTopicMapping;
import com.networknt.eventuate.cdccore.PublishedEvent;
import com.networknt.eventuate.cdccore.kafka.consumer.CdcKafkaConsumer;
import com.networknt.eventuate.common.EventContext;
import com.networknt.eventuate.common.Int128;
import com.networknt.eventuate.common.SubscriberOptions;
import com.networknt.eventuate.common.impl.AggregateEvents;
import com.networknt.eventuate.common.impl.SerializedEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Subscribe to domain events published to Kafka
 *  * @param bootstrapServers Kafka bootstrap Servers string
 */
public class KafkaAggregateSubscriptions implements AggregateEvents {

  private Logger logger = LoggerFactory.getLogger(getClass());

  String bootstrapServers = "localhost:9092";

  public KafkaAggregateSubscriptions(String bootstrapServers) {
    this.bootstrapServers = bootstrapServers;
  }

  public KafkaAggregateSubscriptions() {

  }


  @Override
  public CompletableFuture<?> subscribe(String subscriberId, Map<String, Set<String>> aggregatesAndEvents,
                                        SubscriberOptions subscriberOptions,
                                        Function<SerializedEvent, CompletableFuture<?>> handler) {
    return null;

  }



}
