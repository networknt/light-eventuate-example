
package com.networknt.eventuate.reference.restquery;

import com.networknt.config.Config;
import com.networknt.server.HandlerProvider;
import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Methods;
import com.networknt.info.ServerInfoGetHandler;
import com.networknt.health.HealthGetHandler;
import com.networknt.eventuate.reference.restquery.handler.*;

public class PathHandlerProvider implements HandlerProvider {
    @Override
    public HttpHandler getHandler() {
        return Handlers.routing()
        
            .add(Methods.GET, "/v1/refTable/name/{host}", new RefTableNameHostGetHandler())
        
            .add(Methods.GET, "/v1/refTable/{host}", new RefTableHostGetHandler())
        
            .add(Methods.GET, "/v1/health", new HealthGetHandler())
        
            .add(Methods.GET, "/v1/refTables/{host}", new RefTablesHostGetHandler())
        
            .add(Methods.GET, "/v1/server/info", new ServerInfoGetHandler())
        
            .add(Methods.GET, "/v1/refValue/{id}", new RefValueIdGetHandler())
        
            .add(Methods.GET, "/v1/refTable/host", new RefTableHostsGetHandler())
        
        ;
    }
}
