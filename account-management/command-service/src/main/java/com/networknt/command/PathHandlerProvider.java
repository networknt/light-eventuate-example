package com.networknt.command;

import com.networknt.command.handler.TodosIdDeleteHandler;
import com.networknt.command.handler.TodosPostHandler;
import com.networknt.command.handler.TodosPutHandler;
import com.networknt.server.HandlerProvider;
import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.util.Methods;

public class PathHandlerProvider implements HandlerProvider {

    public HttpHandler getHandler() {
        HttpHandler handler = Handlers.routing()
            .add(Methods.DELETE, "/v1/todos/{id}", new TodosIdDeleteHandler())
            .add(Methods.POST, "/v1/todos", new TodosPostHandler())
            .add(Methods.PUT, "/v1/todos", new TodosPutHandler())
        ;
        return handler;
    }
}

