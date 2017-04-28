package com.networknt.eventuate.todolist.common.event;


import com.networknt.eventuate.common.Event;
import com.networknt.eventuate.common.EventEntity;

@EventEntity(entity = "com.networknt.eventuate.todolist.domain.TodoBulkDeleteAggregate")
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
