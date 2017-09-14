
package com.networknt.eventuate.reference.restcommand.model;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RelationForm {

    
    private String fromValueId;
    
    private String type;
    
    private String toValueId;
    

    public RelationForm () {
    }

    
    
    @JsonProperty("fromValueId")
    public String getFromValueId() {
        return fromValueId;
    }

    public void setFromValueId(String fromValueId) {
        this.fromValueId = fromValueId;
    }
    
    
    
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
    @JsonProperty("toValueId")
    public String getToValueId() {
        return toValueId;
    }

    public void setToValueId(String toValueId) {
        this.toValueId = toValueId;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RelationForm RelationForm = (RelationForm) o;

        return Objects.equals(fromValueId, RelationForm.fromValueId) &&
        Objects.equals(type, RelationForm.type) &&
        
        Objects.equals(toValueId, RelationForm.toValueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromValueId, type,  toValueId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RelationForm {\n");
        
        sb.append("    fromValueId: ").append(toIndentedString(fromValueId)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    toValueId: ").append(toIndentedString(toValueId)).append("\n");
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
