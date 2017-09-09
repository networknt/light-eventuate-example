package com.networknt.eventuate.reference.common.event.refTable;


import com.networknt.eventuate.reference.common.model.ReferenceTable;

public class ReferenceTableCreatedEvent implements ReferenceTableEvent {

    private ReferenceTable referenceTable;

    private ReferenceTableCreatedEvent() {
    }

    public ReferenceTableCreatedEvent(ReferenceTable referenceTable) {
        this.referenceTable = referenceTable;
    }

    public ReferenceTable getReferenceTable() {
        return referenceTable;
    }

    public void setReferenceTable(ReferenceTable referenceTable) {
        this.referenceTable = referenceTable;
    }
}