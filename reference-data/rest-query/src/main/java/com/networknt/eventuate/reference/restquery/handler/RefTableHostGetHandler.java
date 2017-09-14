
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
import java.util.Optional;

public class RefTableHostGetHandler implements HttpHandler {
    private ReferenceRepository referenceRepository = (ReferenceRepository) SingletonServiceFactory.getBean(ReferenceRepository.class);
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        String host = exchange.getQueryParameters().get("host").getFirst();
        String id = exchange.getQueryParameters().get("id").getFirst();
        String name = exchange.getQueryParameters().get("name").getFirst();
        Optional<ReferenceTable> ref = null;
        if (name!=null) {
            ref = referenceRepository.getReferenceByName(host, name);
        } else if (id!=null) {
            ref = referenceRepository.getReferenceById(id);
        }

        String result = null;
        if (ref.isPresent()) {
            result = Config.getInstance().getMapper().writeValueAsString(ref.get());
        } else {
            result = "Not result";
        }

        exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
        exchange.getResponseSender().send(result);
        //     exchange.endExchange();
    }
}
