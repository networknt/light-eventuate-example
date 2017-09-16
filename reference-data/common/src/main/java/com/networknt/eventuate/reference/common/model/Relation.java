package com.networknt.eventuate.reference.common.model;


import java.util.ArrayList;
import java.util.List;

public class Relation implements ValueObject<Relation>{

    private String toValueId;
    private RelationType type;
    private List<Relation> relations = new ArrayList<Relation>();

    public Relation(){}

    public Relation(String toValueId, String type) {
        this.toValueId = toValueId;
        this.type = RelationType.valueOf(type);
    }
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

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public void addRelation(Relation relation) {
        this.relations.add(relation);
    }

    @Override
    public boolean sameValueAs(Relation other) {
        return equals(other);
    }

}
