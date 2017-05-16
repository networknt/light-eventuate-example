package com.networknt.eventuate.reference;


import com.networknt.eventuate.common.DispatchedEvent;
import com.networknt.eventuate.common.EventHandlerMethod;
import com.networknt.eventuate.common.EventSubscriber;

import com.networknt.eventuate.reference.common.event.ReferenceCreatedEvent;
import com.networknt.eventuate.reference.common.event.ReferenceDeletedEvent;
import com.networknt.eventuate.reference.common.event.ReferenceUpdatedEvent;
import com.networknt.eventuate.reference.common.model.ReferenceData;
import com.networknt.service.SingletonServiceFactory;



@EventSubscriber(id = "todoQuerySideEventHandlers")
public class ReferenceQueryEventHandler {

    private ReferenceQueryService service =
            (ReferenceQueryService)SingletonServiceFactory.getBean(ReferenceQueryService.class);

    public ReferenceQueryEventHandler() {
    }

    @EventHandlerMethod
    public void create(DispatchedEvent<ReferenceCreatedEvent> de) {
        ReferenceData ref = de.getEvent().getReferenceData();
        if (service.required(ref.getReferenceName()))  {
            service.save(de.getEntityId(), ref);
        }
    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<ReferenceDeletedEvent> de) {
        service.remove(de.getEntityId());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<ReferenceUpdatedEvent> de) {
        ReferenceData ref = de.getEvent().getReferenceData();
        if (service.required(ref.getReferenceName()))  {
            service.update(de.getEntityId(), ref);
        }
     }
}
