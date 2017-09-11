package com.networknt.eventuate.reference;


import com.networknt.eventuate.common.DispatchedEvent;
import com.networknt.eventuate.common.EventHandlerMethod;
import com.networknt.eventuate.common.EventSubscriber;


import com.networknt.eventuate.reference.common.event.refTable.ReferenceTableCreatedEvent;
import com.networknt.eventuate.reference.common.event.refTable.ReferenceTableDeletedEvent;
import com.networknt.eventuate.reference.common.event.refTable.ReferenceTableUpdatedEvent;
import com.networknt.eventuate.reference.common.event.refValue.ReferenceValueCreatedEvent;
import com.networknt.eventuate.reference.common.event.refValue.ReferenceValueDeletedEvent;
import com.networknt.eventuate.reference.common.event.refValue.ReferenceValueUpdatedEvent;
import com.networknt.eventuate.reference.common.event.refValue.RelationAddedEvent;
import com.networknt.eventuate.reference.common.exception.ReferenceDuplicatedException;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.common.model.ReferenceValue;
import com.networknt.service.SingletonServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@EventSubscriber(id = "referenceQuerySideEventHandlers")
public class ReferenceQueryEventHandler {

    private ReferenceRepository service =
            (ReferenceRepository)SingletonServiceFactory.getBean(ReferenceRepository.class);
    private Logger logger = LoggerFactory.getLogger(getClass());

    public ReferenceQueryEventHandler() {
    }

    @EventHandlerMethod
    public void createRefTable(DispatchedEvent<ReferenceTableCreatedEvent> de) {

        String id = de.getEntityId();
        ReferenceTable ref = de.getEvent().getReferenceTable();
        try {
            service.saveRefTable(id, ref);
        } catch (ReferenceDuplicatedException e) {
            System.out.println("error:" + e.getMessage());
            logger.error("reference creation fail::", ref + " error:" + e.getMessage());

        }
    }

    @EventHandlerMethod
    public void deleteRefTable(DispatchedEvent<ReferenceTableDeletedEvent> de) {
        String id = de.getEntityId();
        service.deleteRefTable(id);
    }

    @EventHandlerMethod
    public void updateRefTable(DispatchedEvent<ReferenceTableUpdatedEvent> de) {
        String id = de.getEntityId();
        ReferenceTable ref = de.getEvent().getReferenceTable();
        service.updateRefTable(id, ref);

     }

    @EventHandlerMethod
    public void createRefValue(DispatchedEvent<ReferenceValueCreatedEvent> de) {

        String id = de.getEntityId();
        ReferenceValue ref = de.getEvent().getReferenceValue();
        service.saveRefValue(id, ref);

    }

    @EventHandlerMethod
    public void deleteRefValue(DispatchedEvent<ReferenceValueDeletedEvent> de) {
        String id = de.getEntityId();
        service.deleteRefValue(id);
    }

    @EventHandlerMethod
    public void updateRefValue(DispatchedEvent<ReferenceValueUpdatedEvent> de) {
        String id = de.getEntityId();
        ReferenceValue ref = de.getEvent().getReferenceValue();
        service.updateRefValue(id, ref);

    }

    @EventHandlerMethod
    public void addRelation(DispatchedEvent<RelationAddedEvent> de) {
        String id = de.getEntityId();
        String type = de.getEvent().getType();
        String toValueId = de.getEvent().getToValueId();
        service.saveRefRelation(type, id, toValueId );

    }

}
