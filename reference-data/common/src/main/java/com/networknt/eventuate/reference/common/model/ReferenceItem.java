package com.networknt.eventuate.reference.common.model;


public class ReferenceItem {
    private String linkStr;
    private String key;
    private String valueEn;
    private String valueFr;
    private String valueOther;

    public String getLinkStr() {
        if (linkStr==null)  {
            return "-";
        }
        return linkStr;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLinkStr(String linkStr) {
        this.linkStr = linkStr;
    }

    public String getValueEn() {
        return valueEn;
    }

    public void setValueEn(String valueEn) {
        this.valueEn = valueEn;
    }

    public String getValueFr() {
        return valueFr;
    }

    public void setValueFr(String valueFr) {
        this.valueFr = valueFr;
    }

    public String getValueOther() {
        return valueOther;
    }

    public void setValueOther(String valueOther) {
        this.valueOther = valueOther;
    }

}
