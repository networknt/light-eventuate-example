
package com.networknt.eventuate.reference.restquery.model;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReferenceTable {

    
    private Boolean common;
    
    private Boolean editable;
    
    private ReferenceValue values;
    
    private String host;
    
    private String tableId;
    
    private Boolean active;
    
    private String tableDesc;
    
    private String tableName;
    

    public ReferenceTable () {
    }

    
    
    @JsonProperty("common")
    public Boolean getCommon() {
        return common;
    }

    public void setCommon(Boolean common) {
        this.common = common;
    }
    
    
    
    @JsonProperty("editable")
    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }
    
    
    
    @JsonProperty("values")
    public ReferenceValue getValues() {
        return values;
    }

    public void setValues(ReferenceValue values) {
        this.values = values;
    }
    
    
    
    @JsonProperty("host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
    
    
    
    @JsonProperty("tableId")
    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
    
    
    
    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    
    
    @JsonProperty("tableDesc")
    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }
    
    
    
    @JsonProperty("tableName")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReferenceTable ReferenceTable = (ReferenceTable) o;

        return Objects.equals(common, ReferenceTable.common) &&
        Objects.equals(editable, ReferenceTable.editable) &&
        Objects.equals(values, ReferenceTable.values) &&
        Objects.equals(host, ReferenceTable.host) &&
        Objects.equals(tableId, ReferenceTable.tableId) &&
        Objects.equals(active, ReferenceTable.active) &&
        Objects.equals(tableDesc, ReferenceTable.tableDesc) &&
        
        Objects.equals(tableName, ReferenceTable.tableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(common, editable, values, host, tableId, active, tableDesc,  tableName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ReferenceTable {\n");
        
        sb.append("    common: ").append(toIndentedString(common)).append("\n");
        sb.append("    editable: ").append(toIndentedString(editable)).append("\n");
        sb.append("    values: ").append(toIndentedString(values)).append("\n");
        sb.append("    host: ").append(toIndentedString(host)).append("\n");
        sb.append("    tableId: ").append(toIndentedString(tableId)).append("\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append("    tableDesc: ").append(toIndentedString(tableDesc)).append("\n");
        sb.append("    tableName: ").append(toIndentedString(tableName)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
