package com.kienast.apiservice.rest.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UpdatedModel
 */

public class UpdatedModel   {
  @JsonProperty("updated")
  private Boolean updated;

  public UpdatedModel updated(Boolean updated) {
    this.updated = updated;
    return this;
  }

  /**
   * Get updated
   * @return updated
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getUpdated() {
    return updated;
  }

  public void setUpdated(Boolean updated) {
    this.updated = updated;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdatedModel updated = (UpdatedModel) o;
    return Objects.equals(this.updated, updated.updated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(updated);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdatedModel {\n");
    
    sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
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

