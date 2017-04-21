package com.networknt.eventuate.todolist.common.common.event;

import java.util.Map;

public class TodoCreatedEvent implements TodoEvent {

    Map<String, Object> todo;

    private TodoCreatedEvent() {
    }

    public TodoCreatedEvent(Map<String, Object> todo) {
        this.todo = todo;
    }

    public Map<String, Object> getTodo() {
        return todo;
    }

    public void setTodo(Map<String, Object> todo) {
        this.todo = todo;
    }
}