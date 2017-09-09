package com.networknt.eventuate.reference.command;


import com.networknt.eventuate.reference.common.model.ReferenceValue;

public class UpdateReferenceValueCommand implements ReferenceCommand {


    private String tableId;

    private ReferenceValue referenceValue;

    public UpdateReferenceValueCommand(String tableId, ReferenceValue referenceValue) {
        this.referenceValue = referenceValue;
        this.tableId = tableId;
    }

    public String getTableId() {
        return tableId;
    }

    public ReferenceValue getReferenceValue() {
        return referenceValue;
    }
}
