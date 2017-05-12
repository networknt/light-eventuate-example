package com.networknt.eventuate.reference.domain;


import com.networknt.eventuate.common.Event;
import com.networknt.eventuate.common.EventUtil;
import com.networknt.eventuate.common.ReflectiveMutableCommandProcessingAggregate;
import com.networknt.eventuate.reference.command.CreateReferenceCommand;
import com.networknt.eventuate.reference.command.DeleteReferenceCommand;
import com.networknt.eventuate.reference.command.ReferenceCommand;
import com.networknt.eventuate.reference.command.UpdateReferenceCommand;
import com.networknt.eventuate.reference.common.event.ReferenceCreatedEvent;
import com.networknt.eventuate.reference.common.event.ReferenceDeletedEvent;
import com.networknt.eventuate.reference.common.event.ReferenceUpdatedEvent;
import com.networknt.eventuate.reference.common.model.ReferenceData;


import java.util.Collections;
import java.util.List;


public class ReferenceDataAggregate extends ReflectiveMutableCommandProcessingAggregate<ReferenceDataAggregate, ReferenceCommand> {

    private ReferenceData referenceData;

    private boolean deleted;

    public List<Event> process(CreateReferenceCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new ReferenceCreatedEvent(cmd.getReferenceData()));
    }

    public List<Event> process(UpdateReferenceCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new ReferenceUpdatedEvent(cmd.getReferenceData()));
    }

    public List<Event> process(DeleteReferenceCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new ReferenceDeletedEvent());
    }


    public void apply(ReferenceCreatedEvent event) {
        this.referenceData = event.getReferenceData();
    }

    public void apply(ReferenceUpdatedEvent event) {
        this.referenceData = event.getReferenceData();
    }

    public void apply(ReferenceDeletedEvent event) {
        this.deleted = true;
    }

    public ReferenceData getReferenceData() {
        return referenceData;
    }

}


