
package com.networknt.eventuate.reference.restquery.handler;

import com.networknt.config.Config;
import com.networknt.eventuate.reference.ReferenceRepository;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefTablesHostGetHandler implements HttpHandler {
    private ReferenceRepository referenceRepository = (ReferenceRepository) SingletonServiceFactory.getBean(ReferenceRepository.class);

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        String host = exchange.getQueryParameters().get("host").getFirst();

        List< ReferenceTable> referenceTables =  referenceRepository.getAllReferences(host);
        String result = null;
        if (referenceTables.size()>0) {
            result = Config.getInstance().getMapper().writeValueAsString(referenceTables);
        } else {
            result = "Not result";
        }

        exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
        exchange.getResponseSender().send(result);
    }
}
