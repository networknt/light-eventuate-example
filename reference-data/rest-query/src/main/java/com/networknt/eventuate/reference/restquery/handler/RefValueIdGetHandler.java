
package com.networknt.eventuate.reference.restquery.handler;

import com.networknt.eventuate.reference.ReferenceRepository;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import java.util.HashMap;
import java.util.Map;

public class RefValueIdGetHandler implements HttpHandler {
    private ReferenceRepository referenceRepository = (ReferenceRepository) SingletonServiceFactory.getBean(ReferenceRepository.class);

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        
            exchange.endExchange();
        
    }
}
