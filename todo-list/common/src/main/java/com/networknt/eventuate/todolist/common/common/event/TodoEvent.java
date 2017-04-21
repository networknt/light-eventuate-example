package com.networknt.eventuate.todolist.common.common.event;


import com.networknt.eventuate.common.Event;
import com.networknt.eventuate.common.EventEntity;

@EventEntity(entity = "net.chrisrichardson.eventstore.examples.todolist.commandside.domain.TodoAggregate")
public interface TodoEvent extends Event {
}
