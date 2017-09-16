package com.networknt.eventuate.reference.common.model;


import java.util.ArrayList;
import java.util.List;


public class ReferenceTable {

    private String  tableId;



    private String  tableName;
    private String tableDesc;
    private String host;

    private boolean active = true;
    private boolean editable = true;
    private boolean common = true;
    private List<ReferenceValue> values = new ArrayList<ReferenceValue>();

    public ReferenceTable() {
    }

    public ReferenceTable(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        if (tableName!=null) {
            return tableName.toUpperCase();
        }
        return tableName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isCommon() {
        return common;
    }

    public void setCommon(boolean common) {
        this.common = common;
    }

    public List<ReferenceValue> getValues() {
        return values;
    }

    public void setValues(List<ReferenceValue> values) {
        this.values = values;
    }

    public void addValue(ReferenceValue value) {
        this.values.add(value);
    }

    public void updateObject(ReferenceTable newRef) {
        setTableName(newRef.getTableName());
        setTableDesc(newRef.getTableDesc());
        setHost(newRef.getHost());
        setActive(newRef.isActive());
        setEditable(newRef.isEditable());
        setCommon(newRef.isCommon());
    }


}
