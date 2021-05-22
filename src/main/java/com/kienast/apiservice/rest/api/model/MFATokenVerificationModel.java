package com.kienast.apiservice.rest.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * MFATokenVerificationModel
 */

public class MFATokenVerificationModel   {
  @JsonProperty("mfaToken")
  private String mfaToken;

  @JsonProperty("jwtToken")
  private String jwtToken;

  @JsonProperty("username")
  private String username;

  public MFATokenVerificationModel mfaToken(String mfaToken) {
    this.mfaToken = mfaToken;
    return this;
  }

  /**
   * Get mfaToken
   * @return mfaToken
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getMfaToken() {
    return mfaToken;
  }

  public void setMfaToken(String mfaToken) {
    this.mfaToken = mfaToken;
  }

  public MFATokenVerificationModel jwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
    return this;
  }

  /**
   * Get jwtToken
   * @return jwtToken
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getJwtToken() {
    return jwtToken;
  }

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }

  public MFATokenVerificationModel username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  */
  @ApiModelProperty(value = "")


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MFATokenVerificationModel mfATokenVerification = (MFATokenVerificationModel) o;
    return Objects.equals(this.mfaToken, mfATokenVerification.mfaToken) &&
        Objects.equals(this.jwtToken, mfATokenVerification.jwtToken) &&
        Objects.equals(this.username, mfATokenVerification.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mfaToken, jwtToken, username);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MFATokenVerificationModel {\n");
    
    sb.append("    mfaToken: ").append(toIndentedString(mfaToken)).append("\n");
    sb.append("    jwtToken: ").append(toIndentedString(jwtToken)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
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

