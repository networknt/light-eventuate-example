
package com.networknt.eventuate.reference.restcommand.model;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ValueLocale {

    
    private String valueId;
    
    private String valueDesc;
    
    
    
    public enum LanguageEnum {
        
        EN ("En"),
        
        FR ("Fr"),
        
        CN ("Cn"),
        
        SP ("Sp");
        

        private final String value;

        LanguageEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static LanguageEnum fromValue(String text) {
            for (LanguageEnum b : LanguageEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                return b;
                }
            }
            return null;
        }
    }

    private LanguageEnum language;

    
    

    public ValueLocale () {
    }

    
    
    @JsonProperty("valueId")
    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }
    
    
    
    @JsonProperty("valueDesc")
    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }
    
    
    
    @JsonProperty("language")
    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ValueLocale ValueLocale = (ValueLocale) o;

        return Objects.equals(valueId, ValueLocale.valueId) &&
        Objects.equals(valueDesc, ValueLocale.valueDesc) &&
        
        Objects.equals(language, ValueLocale.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueId, valueDesc,  language);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ValueLocale {\n");
        
        sb.append("    valueId: ").append(toIndentedString(valueId)).append("\n");
        sb.append("    valueDesc: ").append(toIndentedString(valueDesc)).append("\n");
        sb.append("    language: ").append(toIndentedString(language)).append("\n");
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
