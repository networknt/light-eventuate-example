
package com.networknt.eventuate.reference.restquery.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

public class RefTableHostListGetHandler implements HttpHandler {
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        
            exchange.endExchange();
        
    }
}
