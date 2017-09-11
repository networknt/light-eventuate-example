package com.networknt.eventuate.reference;


import com.networknt.eventuate.common.DispatchedEvent;
import com.networknt.eventuate.common.EventHandlerMethod;
import com.networknt.eventuate.common.EventSubscriber;


import com.networknt.eventuate.reference.common.event.refTable.ReferenceTableCreatedEvent;
import com.networknt.eventuate.reference.common.event.refTable.ReferenceTableDeletedEvent;
import com.networknt.eventuate.reference.common.event.refTable.ReferenceTableUpdatedEvent;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.service.SingletonServiceFactory;



@EventSubscriber(id = "referenceQuerySideEventHandlers")
public class ReferenceQueryEventHandler {

    private ReferenceQueryService service =
            (ReferenceQueryService)SingletonServiceFactory.getBean(ReferenceQueryService.class);

    public ReferenceQueryEventHandler() {
    }

    @EventHandlerMethod
    public void create(DispatchedEvent<ReferenceTableCreatedEvent> de) {
        ReferenceTable ref = de.getEvent().getReferenceTable();

    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<ReferenceTableDeletedEvent> de) {
       // service.remove(de.getEntityId());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<ReferenceTableUpdatedEvent> de) {
        ReferenceTable ref = de.getEvent().getReferenceTable();

     }
}
