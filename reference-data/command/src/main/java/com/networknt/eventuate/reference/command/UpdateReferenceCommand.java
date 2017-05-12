package com.networknt.eventuate.reference.command;

import com.networknt.eventuate.reference.common.model.ReferenceData;


public class UpdateReferenceCommand implements ReferenceCommand {
    private ReferenceData referenceData;

    public UpdateReferenceCommand(String id, ReferenceData referenceData) {
        this.referenceData = referenceData;
    }

    public ReferenceData getReferenceData() {
        return referenceData;
    }
}
