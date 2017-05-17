
package com.networknt.command;

import com.networknt.config.Config;
import com.networknt.server.HandlerProvider;
import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Methods;
import com.networknt.info.ServerInfoGetHandler;
import com.networknt.command.handler.*;

public class PathHandlerProvider implements HandlerProvider {
    @Override
    public HttpHandler getHandler() {
        return Handlers.routing()
        
            .add(Methods.POST, "/v1/reference", new ReferencePostHandler())
        
            .add(Methods.PUT, "/v1/reference", new ReferencePutHandler())
        
            .add(Methods.DELETE, "/v1/reference/{id}", new ReferenceIdDeleteHandler())
        
        ;
    }
}
