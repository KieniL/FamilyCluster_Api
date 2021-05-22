package com.kienast.apiservice.rest.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ResettedModel
 */

public class ResettedModel   {
  @JsonProperty("resetted")
  private Boolean resetted;

  public ResettedModel resetted(Boolean resetted) {
    this.resetted = resetted;
    return this;
  }

  /**
   * Get resetted
   * @return resetted
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getResetted() {
    return resetted;
  }

  public void setResetted(Boolean resetted) {
    this.resetted = resetted;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResettedModel resetted = (ResettedModel) o;
    return Objects.equals(this.resetted, resetted.resetted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resetted);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResettedModel {\n");
    
    sb.append("    resetted: ").append(toIndentedString(resetted)).append("\n");
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

