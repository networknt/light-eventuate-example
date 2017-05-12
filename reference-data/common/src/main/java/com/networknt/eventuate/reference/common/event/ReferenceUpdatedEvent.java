package com.networknt.eventuate.reference.common.event;


import com.networknt.eventuate.reference.common.model.ReferenceData;

public class ReferenceUpdatedEvent implements ReferenceEvent {

    private ReferenceData referenceData;


    private ReferenceUpdatedEvent() {
    }

    public ReferenceUpdatedEvent(ReferenceData referenceData) {
        this.referenceData = referenceData;
    }

    public ReferenceData getReferenceData() {
        return referenceData;
    }

    public void setReferenceData(ReferenceData referenceData) {
        this.referenceData = referenceData;
    }
}