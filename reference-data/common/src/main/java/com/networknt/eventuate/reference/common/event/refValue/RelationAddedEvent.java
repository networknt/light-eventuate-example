package com.networknt.eventuate.reference.common.event.refValue;



public class RelationAddedEvent implements ReferenceValueEvent {

  private String toValueId;

  private String type;

  public RelationAddedEvent() {
  }

  public RelationAddedEvent(String toValueId, String type) {
    this.toValueId = toValueId;
  }

  public String getToValueId() {
    return toValueId;
  }

  public String getType() {
    return type;
  }
}
