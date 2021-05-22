package com.kienast.apiservice.rest.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * VerifiedModel
 */

public class VerifiedModel   {
  @JsonProperty("verificationMessage")
  private String verificationMessage;

  public VerifiedModel verificationMessage(String verificationMessage) {
    this.verificationMessage = verificationMessage;
    return this;
  }

  /**
   * Get verificationMessage
   * @return verificationMessage
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getVerificationMessage() {
    return verificationMessage;
  }

  public void setVerificationMessage(String verificationMessage) {
    this.verificationMessage = verificationMessage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VerifiedModel verified = (VerifiedModel) o;
    return Objects.equals(this.verificationMessage, verified.verificationMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(verificationMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VerifiedModel {\n");
    
    sb.append("    verificationMessage: ").append(toIndentedString(verificationMessage)).append("\n");
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

