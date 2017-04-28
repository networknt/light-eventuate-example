package com.networknt.command.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.networknt.config.Config;
import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EventuateAggregateStore;
import com.networknt.eventuate.common.impl.sync.AggregateCrud;
import com.networknt.eventuate.todolist.TodoCommandService;
import com.networknt.eventuate.todolist.TodoCommandServiceImpl;
import com.networknt.eventuate.todolist.common.model.TodoInfo;
import com.networknt.eventuate.todolist.domain.TodoAggregate;
import com.networknt.eventuate.todolist.domain.TodoBulkDeleteAggregate;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import com.networknt.body.BodyHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class TodosPostHandler implements HttpHandler {

    private AggregateCrud aggregateCrud = (AggregateCrud)SingletonServiceFactory.getBean(AggregateCrud.class);
    private EventuateAggregateStore eventStore  = (EventuateAggregateStore)SingletonServiceFactory.getBean(EventuateAggregateStore.class);
    // private TodoCommandService service = (TodoCommandService)SingletonServiceFactory.getBean(TodoCommandService.class);

    private AggregateRepository todoRepository = new AggregateRepository(TodoAggregate.class, eventStore);
    private AggregateRepository bulkDeleteAggregateRepository  = new AggregateRepository(TodoBulkDeleteAggregate.class, eventStore);

    private TodoCommandService  service = new TodoCommandServiceImpl(todoRepository, bulkDeleteAggregateRepository);


    public void handleRequest(HttpServerExchange exchange) throws Exception {
        // add a new object
        TodoInfo todo = (TodoInfo)exchange.getAttachment(BodyHandler.REQUEST_BODY);
        CompletableFuture<TodoInfo> result = service.add(todo).thenApply((e) -> {
            TodoInfo m = e.getAggregate().getTodo();
            return m;
        });
        exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
        exchange.getResponseSender().send(Config.getInstance().getMapper().writeValueAsString(result));
    }
}
