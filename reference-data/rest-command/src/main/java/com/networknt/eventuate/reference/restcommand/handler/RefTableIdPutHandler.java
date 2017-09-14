
package com.networknt.eventuate.reference.restcommand.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.body.BodyHandler;
import com.networknt.config.Config;
import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EventuateAggregateStore;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.domain.ReferenceTableAggregate;
import com.networknt.eventuate.reference.domain.ReferenceValueAggregate;
import com.networknt.eventuate.reference.service.*;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RefTableIdPutHandler implements HttpHandler {
    private EventuateAggregateStore eventStore  = (EventuateAggregateStore) SingletonServiceFactory.getBean(EventuateAggregateStore.class);

    private AggregateRepository refTableRepository = new AggregateRepository(ReferenceTableAggregate.class, eventStore);
    private AggregateRepository refValueRepository = new AggregateRepository(ReferenceValueAggregate.class, eventStore);

    private ReferenceTableCommandService tableService = new ReferenceTableCommandServiceImpl(refTableRepository);
    private ReferenceValueCommandService valueService = new ReferenceValueCommandServiceImpl(refValueRepository);
    private ReferenceCommandService service = new ReferenceCommandServiceImpl(tableService, valueService);
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        String id = exchange.getQueryParameters().get("id").getFirst();

        ObjectMapper mapper = new ObjectMapper();

        Map s = (Map)exchange.getAttachment(BodyHandler.REQUEST_BODY);
        String json = mapper.writeValueAsString(s);
        ReferenceTable referenceTable = mapper.readValue(json, ReferenceTable.class);

        CompletableFuture<ReferenceTable> result = service.updateRefTable(id, referenceTable).thenApply((e) -> {
            ReferenceTable m =  new ReferenceTable();
            m.setTableId(e.getEntityId());
            m.setHost(e.getAggregate().getReferenceData().getHost());
            m.setTableName(e.getAggregate().getReferenceData().getTableName());
            m.setTableDesc(e.getAggregate().getReferenceData().getTableDesc());
            return m;
        });

        exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
        exchange.getResponseSender().send(Config.getInstance().getMapper().writeValueAsString(result.get()));
        
    }
}
