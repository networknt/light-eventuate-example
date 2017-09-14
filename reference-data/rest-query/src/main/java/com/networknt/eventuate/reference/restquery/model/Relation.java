
package com.networknt.eventuate.reference.restquery.model;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Relation {

    
    private String type;
    
    private Relation relations;
    
    private String toValueId;
    

    public Relation () {
    }

    
    
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
    @JsonProperty("relations")
    public Relation getRelations() {
        return relations;
    }

    public void setRelations(Relation relations) {
        this.relations = relations;
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
        Relation Relation = (Relation) o;

        return Objects.equals(type, Relation.type) &&
        Objects.equals(relations, Relation.relations) &&
        
        Objects.equals(toValueId, Relation.toValueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, relations,  toValueId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Relation {\n");
        
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    relations: ").append(toIndentedString(relations)).append("\n");
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
