package com.networknt.query.handler;

import com.networknt.config.Config;
import com.networknt.eventuate.todolist.TodoQueryService;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.util.Collection;
import java.util.Map;

public class TodosGetHandler implements HttpHandler {

    TodoQueryService service =
            (TodoQueryService)SingletonServiceFactory.getBean(TodoQueryService.class);

    public void handleRequest(HttpServerExchange exchange) throws Exception {
        Collection<Map<String, Object>> result = service.getAll();
        exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
        exchange.getResponseSender().send(Config.getInstance().getMapper().writeValueAsString(result));
    }
}
