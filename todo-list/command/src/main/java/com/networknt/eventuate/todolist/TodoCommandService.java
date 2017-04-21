package com.networknt.eventuate.todolist;

import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.todolist.domain.TodoAggregate;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by stevehu on 2016-12-10.
 */
public interface TodoCommandService {

    CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> add(Map<String, Object> todo);

    CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> remove(String id);

    CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> update(Map<String, Object> newTodo);
}
