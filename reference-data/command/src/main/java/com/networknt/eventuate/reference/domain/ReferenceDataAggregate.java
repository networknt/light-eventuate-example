package com.networknt.eventuate.reference.domain;


import com.networknt.eventuate.common.Event;
import com.networknt.eventuate.common.EventUtil;
import com.networknt.eventuate.common.ReflectiveMutableCommandProcessingAggregate;
import com.networknt.eventuate.reference.command.CreateReferenceTableCommand;
import com.networknt.eventuate.reference.command.DeleteReferenceTableCommand;
import com.networknt.eventuate.reference.command.ReferenceCommand;
import com.networknt.eventuate.reference.command.UpdateReferenceTableCommand;

import com.networknt.eventuate.reference.common.event.refTable.ReferenceTableCreatedEvent;
import com.networknt.eventuate.reference.common.event.refTable.ReferenceTableDeletedEvent;
import com.networknt.eventuate.reference.common.event.refTable.ReferenceTableUpdatedEvent;
import com.networknt.eventuate.reference.common.model.ReferenceTable;


import java.util.Collections;
import java.util.List;


public class ReferenceDataAggregate extends ReflectiveMutableCommandProcessingAggregate<ReferenceDataAggregate, ReferenceCommand> {

    private ReferenceTable referenceTable;

    private boolean deleted;

    public List<Event> process(CreateReferenceTableCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new ReferenceTableCreatedEvent(cmd.getReferenceTable()));
    }

    public List<Event> process(UpdateReferenceTableCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new ReferenceTableUpdatedEvent(cmd.getReferenceTable()));
    }

    public List<Event> process(DeleteReferenceTableCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new ReferenceTableDeletedEvent());
    }


    public void apply(ReferenceTableCreatedEvent event) {
        this.referenceTable = event.getReferenceTable();
    }

    public void apply(ReferenceTableUpdatedEvent event) {
        this.referenceTable = event.getReferenceTable();
    }

    public void apply(ReferenceTableDeletedEvent event) {
        this.deleted = true;
    }

    public ReferenceTable getReferenceData() {
        return referenceTable;
    }

}


