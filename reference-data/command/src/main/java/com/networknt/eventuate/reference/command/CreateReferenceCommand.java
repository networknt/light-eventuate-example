package com.networknt.eventuate.reference.command;


import com.networknt.eventuate.reference.common.model.ReferenceData;

public class CreateReferenceCommand implements ReferenceCommand {

    private ReferenceData referenceData;

    public CreateReferenceCommand(ReferenceData referenceData) {
        this.referenceData = referenceData;
    }

    public ReferenceData getReferenceData() {
        return referenceData;
    }
}
