package com.networknt.query.handler;

import com.networknt.config.Config;
import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EventuateAggregateStore;
import com.networknt.eventuate.todolist.TodoCommandService;
import com.networknt.eventuate.todolist.TodoCommandServiceImpl;
import com.networknt.eventuate.todolist.TodoQueryService;
import com.networknt.eventuate.todolist.common.model.TodoInfo;
import com.networknt.eventuate.todolist.domain.TodoAggregate;
import com.networknt.eventuate.todolist.domain.TodoBulkDeleteAggregate;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class TodosGetHandler implements HttpHandler {


    TodoQueryService service =
            (TodoQueryService)SingletonServiceFactory.getBean(TodoQueryService.class);

    public void handleRequest(HttpServerExchange exchange) throws Exception {

        List<Map<String, TodoInfo>> resultAll = service.getAll();
        exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
        exchange.getResponseSender().send(Config.getInstance().getMapper().writeValueAsString(resultAll));
    }
}
