
package com.networknt.eventuate.reference.restcommand;

import com.networknt.config.Config;
import com.networknt.server.HandlerProvider;
import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Methods;
import com.networknt.info.ServerInfoGetHandler;
import com.networknt.health.HealthGetHandler;
import com.networknt.eventuate.reference.restcommand.handler.*;

public class PathHandlerProvider implements HandlerProvider {
    @Override
    public HttpHandler getHandler() {
        return Handlers.routing()
        
            .add(Methods.GET, "/v1/health", new HealthGetHandler())
        
            .add(Methods.DELETE, "/v1/refTable/{id}", new RefTableIdDeleteHandler())
        
            .add(Methods.PUT, "/v1/refTable/{id}", new RefTableIdPutHandler())
        
            .add(Methods.GET, "/v1/server/info", new ServerInfoGetHandler())
        
            .add(Methods.DELETE, "/v1/refValue/{id}", new RefValueIdDeleteHandler())
        
            .add(Methods.PUT, "/v1/refValue/{id}", new RefValueIdPutHandler())
        
            .add(Methods.POST, "/v1/refValue/relation", new RefValueRelationPostHandler())
        
            .add(Methods.POST, "/v1/refTable", new RefTablePostHandler())
        
            .add(Methods.POST, "/v1/refValue", new RefValuePostHandler())
        
        ;
    }
}
