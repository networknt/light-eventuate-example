package com.networknt.eventuate.todolist.common.event;


import com.networknt.eventuate.common.Event;
import com.networknt.eventuate.common.EventEntity;

@EventEntity(entity = "net.chrisrichardson.eventstore.examples.todolist.commandside.domain.TodoBulkDeleteAggregate")
public class TodoDeletionRequestedEvent implements Event {

    private String todoId;

    public TodoDeletionRequestedEvent(String todoId) {
        this.todoId = todoId;
    }

    public TodoDeletionRequestedEvent() {

    }

    public String getTodoId() {
        return todoId;
    }
}
