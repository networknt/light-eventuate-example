package com.networknt.eventuate.reference.domain;


import com.networknt.eventuate.common.Event;
import com.networknt.eventuate.common.EventUtil;
import com.networknt.eventuate.common.ReflectiveMutableCommandProcessingAggregate;
import com.networknt.eventuate.reference.command.*;


import com.networknt.eventuate.reference.common.event.refValue.ReferenceValueCreatedEvent;
import com.networknt.eventuate.reference.common.event.refValue.ReferenceValueDeletedEvent;
import com.networknt.eventuate.reference.common.event.refValue.ReferenceValueUpdatedEvent;

import com.networknt.eventuate.reference.common.event.refValue.RelationAddedEvent;
import com.networknt.eventuate.reference.common.model.ReferenceValue;

import java.util.Collections;
import java.util.List;


public class ReferenceValueAggregate extends ReflectiveMutableCommandProcessingAggregate<ReferenceValueAggregate, ReferenceCommand> {

    private ReferenceValue referenceValue;
    private String tableId;
    private boolean deleted;

    public List<Event> process(CreateReferenceValueCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new ReferenceValueCreatedEvent(cmd.getTableId(), cmd.getReferenceValue()));
    }

    public List<Event> process(UpdateReferenceValueCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new ReferenceValueUpdatedEvent(cmd.getTableId(), cmd.getReferenceValue()));
    }

    public List<Event> process(RelationAddCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new RelationAddedEvent(cmd.getToValueId(), cmd.getType()));
    }

    public List<Event> process(DeleteReferenceValueCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new ReferenceValueDeletedEvent());
    }


    public void apply(ReferenceValueCreatedEvent event) {
        this.tableId = tableId;
        this.referenceValue = event.getReferenceValue();
    }

    public void apply(ReferenceValueUpdatedEvent event) {
        this.tableId = tableId;
        this.referenceValue = event.getReferenceValue();
    }

    public void apply(ReferenceValueDeletedEvent event) {
        this.deleted = true;
    }

    public void apply(RelationAddedEvent event) {
    }

    public ReferenceValue getReferenceValue() {
        return referenceValue;
    }

    public String getTableId() {
        return tableId;
    }
}


