package com.networknt.eventuate.reference.common.event;


import com.networknt.eventuate.reference.common.model.ReferenceData;

public class ReferenceCreatedEvent implements ReferenceEvent {

    private ReferenceData referenceData;

    private ReferenceCreatedEvent() {
    }

    public ReferenceCreatedEvent(ReferenceData referenceData) {
        this.referenceData = referenceData;
    }

    public ReferenceData getReferenceData() {
        return referenceData;
    }

    public void setReferenceData(ReferenceData referenceData) {
        this.referenceData = referenceData;
    }
}