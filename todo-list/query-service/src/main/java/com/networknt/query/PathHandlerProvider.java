package com.networknt.query;

import com.networknt.query.handler.TodosGetHandler;
import com.networknt.query.handler.TodosIdGetHandler;
import com.networknt.server.HandlerProvider;
import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.util.Methods;

public class PathHandlerProvider implements HandlerProvider {

    public HttpHandler getHandler() {
        HttpHandler handler = Handlers.routing()
            .add(Methods.GET, "/v1/todos", new TodosGetHandler())
            .add(Methods.GET, "/v1/todos/{id}", new TodosIdGetHandler())
        ;
        return handler;
    }
}

