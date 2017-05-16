package com.networknt.eventuate.reference.domain;

/**
 * reference data status on query side
 *  CREATED : new created reference data, triggered by createEvent
 *  UPDATED: updated reference data, triggered by updateEvent
 *  APPROVED: approved, it can be use from query side
 */
public enum Status {
    CREATED("C"), UPDATED("U"), APPROVED("A");

    private String statusCode;

    Status (String code) {
        statusCode = code;
    }
    public String  asString(){
        return statusCode;
    }

}
