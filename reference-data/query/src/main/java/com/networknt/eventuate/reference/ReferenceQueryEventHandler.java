package com.networknt.eventuate.reference;


import com.networknt.eventuate.common.DispatchedEvent;
import com.networknt.eventuate.common.EventHandlerMethod;
import com.networknt.eventuate.common.EventSubscriber;

import com.networknt.eventuate.reference.common.event.ReferenceTableCreatedEvent;
import com.networknt.eventuate.reference.common.event.ReferenceTableDeletedEvent;
import com.networknt.eventuate.reference.common.event.ReferenceTableUpdatedEvent;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.service.SingletonServiceFactory;



@EventSubscriber(id = "todoQuerySideEventHandlers")
public class ReferenceQueryEventHandler {

    private ReferenceQueryService service =
            (ReferenceQueryService)SingletonServiceFactory.getBean(ReferenceQueryService.class);

    public ReferenceQueryEventHandler() {
    }

    @EventHandlerMethod
    public void create(DispatchedEvent<ReferenceTableCreatedEvent> de) {
        ReferenceTable ref = de.getEvent().getReferenceData();
        if (service.required(ref.getReferenceName()))  {
            service.save(de.getEntityId(), ref);
        }
    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<ReferenceTableDeletedEvent> de) {
        service.remove(de.getEntityId());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<ReferenceTableUpdatedEvent> de) {
        ReferenceTable ref = de.getEvent().getReferenceData();
        if (service.required(ref.getReferenceName()))  {
            service.update(de.getEntityId(), ref);
        }
     }
}
