
package com.networknt.eventuate.reference.common.model;

/**
 * Represents a language value for the ref-value.
 */
public class ValueLocale implements ValueObject<ValueLocale> {

  private Language language;
  private String valueId;
  private String valueDesc;

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public String getValueId() {
    return valueId;
  }

  public void setValueId(String valueId) {
    this.valueId = valueId;
  }

  public String getValueDesc() {
    return valueDesc;
  }

  public void setValueDesc(String valueDesc) {
    this.valueDesc = valueDesc;
  }

  @Override
  public boolean sameValueAs(ValueLocale other) {
    return equals(other);
  }

}
