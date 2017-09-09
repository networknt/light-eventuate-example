package com.networknt.eventuate.reference.command;

import com.networknt.eventuate.reference.common.model.ReferenceTable;


public class UpdateReferenceTableCommand implements ReferenceCommand {
    private ReferenceTable referenceTable;

    public UpdateReferenceTableCommand( ReferenceTable referenceTable) {
        this.referenceTable = referenceTable;
    }

    public ReferenceTable getReferenceTable() {
        return referenceTable;
    }
}
