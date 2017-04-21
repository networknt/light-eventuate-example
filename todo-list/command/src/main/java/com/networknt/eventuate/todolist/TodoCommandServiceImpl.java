package com.networknt.eventuate.todolist;

import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.common.EventuateAggregateStore;
import com.networknt.eventuate.todolist.command.CreateTodoCommand;
import com.networknt.eventuate.todolist.command.DeleteTodoCommand;
import com.networknt.eventuate.todolist.command.TodoCommand;
import com.networknt.eventuate.todolist.command.UpdateTodoCommand;
import com.networknt.eventuate.todolist.domain.TodoAggregate;
import com.networknt.service.SingletonServiceFactory;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by stevehu on 2016-12-10.
 */
public class TodoCommandServiceImpl implements TodoCommandService {

    private AggregateRepository<TodoAggregate, TodoCommand> aggregateRepository;

    public TodoCommandServiceImpl() {
        EventuateAggregateStore store =
                (EventuateAggregateStore) SingletonServiceFactory.getBean(EventuateAggregateStore.class);
        this.aggregateRepository =
                new AggregateRepository<>(TodoAggregate.class, store);
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> add(Map<String, Object> todo) {
        return aggregateRepository.save(new CreateTodoCommand(todo));
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> remove(String id) {
        return aggregateRepository.update(id, new DeleteTodoCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> update(Map<String, Object> newTodo) {
        return aggregateRepository.update((String)newTodo.get("id"), new UpdateTodoCommand(newTodo));
    }
}
