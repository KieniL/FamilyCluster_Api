package com.kienast.apiservice.rest.api.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ChangedModel
 */

public class ChangedModel   {
  @JsonProperty("changed")
  private Boolean changed;

  public ChangedModel changed(Boolean changed) {
    this.changed = changed;
    return this;
  }

  /**
   * Get changed
   * @return changed
  */
  @ApiModelProperty(value = "")


  public Boolean getChanged() {
    return changed;
  }

  public void setChanged(Boolean changed) {
    this.changed = changed;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangedModel changed = (ChangedModel) o;
    return Objects.equals(this.changed, changed.changed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(changed);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangedModel {\n");
    
    sb.append("    changed: ").append(toIndentedString(changed)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

