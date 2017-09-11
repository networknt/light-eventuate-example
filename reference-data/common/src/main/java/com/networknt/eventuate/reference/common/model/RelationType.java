

package com.networknt.eventuate.reference.common.model;



public enum RelationType {

  A101("Link"),
  A102("Sub-value");

  private String type;

  RelationType(String type) {
    this.type = type;
  }
}
