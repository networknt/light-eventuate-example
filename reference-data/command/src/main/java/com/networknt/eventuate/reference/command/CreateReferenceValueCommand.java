package com.networknt.eventuate.reference.command;


import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.common.model.ReferenceValue;

public class CreateReferenceValueCommand implements ReferenceCommand {


    private String tableId;

    private ReferenceValue referenceValue;

    public CreateReferenceValueCommand(String tableId, ReferenceValue referenceValue) {
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
