
package com.networknt.eventuate.customer.command.handler;

import com.networknt.eventuate.account.command.customer.Customer;
import com.networknt.eventuate.account.command.customer.CustomerService;
import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EventuateAggregateStore;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import java.util.HashMap;
import java.util.Map;

public class ToaccountsCustomerIdAccountIdDeleteHandler implements HttpHandler {
    private EventuateAggregateStore eventStore  = (EventuateAggregateStore) SingletonServiceFactory.getBean(EventuateAggregateStore.class);
    private AggregateRepository customertRepository = new AggregateRepository(Customer.class, eventStore);

    private CustomerService service = new CustomerService(customertRepository);
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        
            exchange.endExchange();
        
    }
}
