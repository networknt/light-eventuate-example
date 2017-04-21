package com.networknt.eventuate.todolist.common.common.event;


import java.util.Map;

public class TodoUpdatedEvent implements TodoEvent {

    private Map<String, Object> todo;

    private TodoUpdatedEvent() {
    }

    public TodoUpdatedEvent(Map<String, Object> todo) {
        this.todo = todo;
    }

    public Map<String, Object> getTodo() {
        return todo;
    }

    public void setTodo(Map<String, Object> todo) {
        this.todo = todo;
    }
}