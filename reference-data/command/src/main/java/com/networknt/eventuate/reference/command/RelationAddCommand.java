package com.networknt.eventuate.reference.command;


import com.networknt.eventuate.reference.common.model.ReferenceValue;

public class RelationAddCommand implements ReferenceCommand {

    private String toValueId;

    private String type;

    public RelationAddCommand(String toValueId, String type) {
        this.toValueId = toValueId;
        this.type = type;
    }

    public String getToValueId() {
        return toValueId;
    }

    public String getType() {
        return type;
    }
}
