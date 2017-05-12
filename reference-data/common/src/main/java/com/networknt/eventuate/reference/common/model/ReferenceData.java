package com.networknt.eventuate.reference.common.model;


import java.util.Map;

public class ReferenceData {
    private String  referenceName;
    private String description;
    private Map<String, ReferenceItem> content;
    private boolean active = true;


    public ReferenceData() {
    }

    public ReferenceData(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getReferenceName() {
        if (referenceName!=null) {
            return referenceName.toUpperCase();
        }
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Map<String, ReferenceItem> getContent() {
        return content;
    }

    public void setContent(Map<String, ReferenceItem> content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
