package com.networknt.eventuate.reference.common.model;


import java.util.List;


public class ReferenceData {
    private String  referenceName;
    private String description;
    private List<ReferenceItem> content;
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

    public List<ReferenceItem> getContent() {
        return content;
    }

    public void setContent(List<ReferenceItem> content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
