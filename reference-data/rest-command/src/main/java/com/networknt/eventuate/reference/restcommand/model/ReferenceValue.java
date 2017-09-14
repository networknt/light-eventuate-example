
package com.networknt.eventuate.reference.restcommand.model;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReferenceValue {

    
    private String valueId;
    
    private ValueLocale locales;
    
    private java.math.BigDecimal displayOrder;
    
    private String tableId;
    
    private String valueCode;
    
    private java.time.LocalDateTime startTime;
    
    private java.time.LocalDateTime endTime;
    
    private Relation relations;
    

    public ReferenceValue () {
    }

    
    
    @JsonProperty("valueId")
    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }
    
    
    
    @JsonProperty("locales")
    public ValueLocale getLocales() {
        return locales;
    }

    public void setLocales(ValueLocale locales) {
        this.locales = locales;
    }
    
    
    
    @JsonProperty("displayOrder")
    public java.math.BigDecimal getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(java.math.BigDecimal displayOrder) {
        this.displayOrder = displayOrder;
    }
    
    
    
    @JsonProperty("tableId")
    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
    
    
    
    @JsonProperty("valueCode")
    public String getValueCode() {
        return valueCode;
    }

    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }
    
    
    
    @JsonProperty("startTime")
    public java.time.LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(java.time.LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    
    
    @JsonProperty("endTime")
    public java.time.LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(java.time.LocalDateTime endTime) {
        this.endTime = endTime;
    }
    
    
    
    @JsonProperty("relations")
    public Relation getRelations() {
        return relations;
    }

    public void setRelations(Relation relations) {
        this.relations = relations;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReferenceValue ReferenceValue = (ReferenceValue) o;

        return Objects.equals(valueId, ReferenceValue.valueId) &&
        Objects.equals(locales, ReferenceValue.locales) &&
        Objects.equals(displayOrder, ReferenceValue.displayOrder) &&
        Objects.equals(tableId, ReferenceValue.tableId) &&
        Objects.equals(valueCode, ReferenceValue.valueCode) &&
        Objects.equals(startTime, ReferenceValue.startTime) &&
        Objects.equals(endTime, ReferenceValue.endTime) &&
        
        Objects.equals(relations, ReferenceValue.relations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueId, locales, displayOrder, tableId, valueCode, startTime, endTime,  relations);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ReferenceValue {\n");
        
        sb.append("    valueId: ").append(toIndentedString(valueId)).append("\n");
        sb.append("    locales: ").append(toIndentedString(locales)).append("\n");
        sb.append("    displayOrder: ").append(toIndentedString(displayOrder)).append("\n");
        sb.append("    tableId: ").append(toIndentedString(tableId)).append("\n");
        sb.append("    valueCode: ").append(toIndentedString(valueCode)).append("\n");
        sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
        sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
        sb.append("    relations: ").append(toIndentedString(relations)).append("\n");
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
