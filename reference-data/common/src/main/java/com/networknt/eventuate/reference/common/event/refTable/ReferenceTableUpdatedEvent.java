package com.networknt.eventuate.reference.common.event.refTable;


import com.networknt.eventuate.reference.common.model.ReferenceTable;

public class ReferenceTableUpdatedEvent implements ReferenceTableEvent {

    private ReferenceTable referenceTable;


    private ReferenceTableUpdatedEvent() {
    }

    public ReferenceTableUpdatedEvent(ReferenceTable referenceTable) {
        this.referenceTable = referenceTable;
    }

    public ReferenceTable getReferenceTable() {
        return referenceTable;
    }

    public void setReferenceTable(ReferenceTable referenceTable) {
        this.referenceTable = referenceTable;
    }
}