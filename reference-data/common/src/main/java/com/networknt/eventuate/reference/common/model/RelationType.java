

package com.networknt.eventuate.reference.common.model;



public enum RelationType {

  Link("Link with other"),
  SubValue("Sub-value of the parent value");

  private String type;

  RelationType(String type) {
    this.type = type;
  }
}
