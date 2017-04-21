package com.networknt.eventuate.todolist.command;

import java.util.Map;

public class CreateTodoCommand implements TodoCommand {

    private Map<String, Object> todo;

    public CreateTodoCommand(Map<String, Object> todo) {
        this.todo = todo;
    }

    public Map<String, Object> getTodo() {
        return todo;
    }
}
