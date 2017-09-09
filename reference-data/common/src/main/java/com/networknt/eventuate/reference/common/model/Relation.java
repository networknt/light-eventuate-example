package com.networknt.eventuate.reference.common.model;



public class Relation implements ValueObject<Relation>{

    private String toValueId;
    private RelationType type;

    public String getToValueId() {
        return toValueId;
    }

    public void setToValueId(String toValueId) {
        this.toValueId = toValueId;
    }

    public RelationType getType() {
        return type;
    }

    public void setType(RelationType type) {
        this.type = type;
    }

    @Override
    public boolean sameValueAs(Relation other) {
        return equals(other);
    }

}
