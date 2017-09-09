package com.networknt.eventuate.reference.command;


import com.networknt.eventuate.reference.common.model.ReferenceTable;

public class CreateReferenceTableCommand implements ReferenceCommand {

    private ReferenceTable referenceTable;

    public CreateReferenceTableCommand(ReferenceTable referenceTable) {
        this.referenceTable = referenceTable;
    }

    public ReferenceTable getReferenceTable() {
        return referenceTable;
    }
}
