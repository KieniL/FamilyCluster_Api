package com.kienast.apiservice.rest.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * PasswordModel
 */

public class PasswordModel   {
  @JsonProperty("password")
  private String password;

  @JsonProperty("jwt")
  private String jwt;

  public PasswordModel password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public PasswordModel jwt(String jwt) {
    this.jwt = jwt;
    return this;
  }

  /**
   * Get jwt
   * @return jwt
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PasswordModel password = (PasswordModel) o;
    return Objects.equals(this.password, password.password) &&
        Objects.equals(this.jwt, password.jwt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password, jwt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PasswordModel {\n");
    
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    jwt: ").append(toIndentedString(jwt)).append("\n");
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

