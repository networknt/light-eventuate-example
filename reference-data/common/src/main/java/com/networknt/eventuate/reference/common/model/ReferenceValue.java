package com.networknt.eventuate.reference.common.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class ReferenceValue implements Comparator, ValueObject<ReferenceValue>{
    private String tableId;
    private String valueId;
    private String valueCode;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Timestamp startTime;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Timestamp endTime;
    private int displayOrder;
    private boolean active = true;

    private Set<ValueLocale> locales = new HashSet<ValueLocale>();
    private List<Relation> relations = new ArrayList<Relation>();

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getValueCode() {
        return valueCode;
    }

    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }


    public Set<ValueLocale> getLocales() {
        return locales;
    }

    public void setLocales(Set<ValueLocale> locales) {
        this.locales = locales;
    }

    public void addLocale(ValueLocale locale) {
        this.locales.add(locale);
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
    public boolean sameValueAs(ReferenceValue other) {
        return equals(other);
    }

    @Override
    public int compare(Object value1, Object value2) {
        int order1 = ((ReferenceValue) value1).getDisplayOrder();
        int order2 = ((ReferenceValue) value1).getDisplayOrder();
        if (order1<order2) {
            return -1;
        } else if (order1==order2) {
            return 0;
        } else {
            return 1;
        }

    }

    public void updateObject(ReferenceValue newValue) {
        setValueCode(newValue.getValueCode());
        setDisplayOrder(newValue.getDisplayOrder());
        setStartTime(newValue.getStartTime());
        setEndTime(newValue.getEndTime());
        setActive(newValue.isActive());
    }
}
