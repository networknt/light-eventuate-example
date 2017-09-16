
package com.networknt.eventuate.reference.restquery.handler;

import com.networknt.config.Config;
import com.networknt.eventuate.reference.ReferenceRepository;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.util.List;
import java.util.Optional;

public class RefTableHostsGetHandler implements HttpHandler {
    private ReferenceRepository referenceRepository = (ReferenceRepository) SingletonServiceFactory.getBean(ReferenceRepository.class);

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        List<String> hosts = referenceRepository.getAllHosts();

        String result = null;

        if (hosts.size()>0) {
            result = Config.getInstance().getMapper().writeValueAsString(hosts);
        } else {
            result = "Not result";
        }

        exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
        exchange.getResponseSender().send(result);
        //     exchange.endExchange();

    }
}
