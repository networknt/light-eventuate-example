package com.networknt.query.handler;

import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EventuateAggregateStore;
import com.networknt.eventuate.todolist.TodoCommandService;
import com.networknt.eventuate.todolist.TodoCommandServiceImpl;
import com.networknt.eventuate.todolist.domain.TodoAggregate;
import com.networknt.eventuate.todolist.domain.TodoBulkDeleteAggregate;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.util.concurrent.CompletableFuture;

public class TodosCreateEventHandler implements HttpHandler {

    private EventuateAggregateStore eventStore  = (EventuateAggregateStore)SingletonServiceFactory.getBean(EventuateAggregateStore.class);

    private AggregateRepository todoRepository = new AggregateRepository(TodoAggregate.class, eventStore);
    private AggregateRepository bulkDeleteAggregateRepository  = new AggregateRepository(TodoBulkDeleteAggregate.class, eventStore);

    private TodoCommandService commandService = new TodoCommandServiceImpl(todoRepository, bulkDeleteAggregateRepository);



    public void handleRequest(HttpServerExchange exchange) throws Exception {

        TodoInfo todo = new TodoInfo();
        todo.setTitle(" this is the integration test todo from create event URL");
        CompletableFuture<TodoInfo> result = commandService.add(todo).thenApply((e) -> {
            TodoInfo m = e.getAggregate().getTodo();
            System.out.println("m = " + m);
            System.out.println("m = " + e.getEntityId());
            return m;
        });

        System.out.println(" = " + result.get());


        exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
        exchange.getResponseSender().send(result.get().getTitle());
    }
}
