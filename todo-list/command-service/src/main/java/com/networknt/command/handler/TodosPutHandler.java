package com.networknt.command.handler;

import com.networknt.body.BodyHandler;
import com.networknt.config.Config;
import com.networknt.eventuate.todolist.TodoCommandService;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.StringEscapeUtils;

public class TodosPutHandler implements HttpHandler {
    TodoCommandService service = (TodoCommandService) SingletonServiceFactory.getBean(TodoCommandService.class);

    public void handleRequest(HttpServerExchange exchange) throws Exception {
        // update a new object
        Map<String, Object> todo = (Map)exchange.getAttachment(BodyHandler.REQUEST_BODY);
        CompletableFuture<Map<String, Object>> result = service.update(todo).thenApply((e) -> {
            Map<String, Object> m = e.getAggregate().getTodo();
            return m;
        });
        exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
        exchange.getResponseSender().send(Config.getInstance().getMapper().writeValueAsString(result));
    }
}
