package com.networknt.eventuate.todolist;

import com.networknt.eventuate.common.SubscriberOptions;
import com.networknt.eventuate.common.impl.AggregateEvents;
import com.networknt.eventuate.common.impl.SerializedEvent;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Created by gavin on 2017-05-19.
 */
public class EventQueryEvents implements AggregateEvents {
    @Override
    public CompletableFuture<?>  subscribe(String subscriberId, Map<String, Set<String>> aggregatesAndEvents, SubscriberOptions options, Function<SerializedEvent, CompletableFuture<?>> handler) {
         return null;
     }


    }
