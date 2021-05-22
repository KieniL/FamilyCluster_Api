package com.kienast.apiservice.rest.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * AuthenticationModel
 */

public class AuthenticationModel   {
  @JsonProperty("token")
  private String token;

  @JsonProperty("allowedApplicationList")
  @Valid
  private List<AllowedApplicationModel> allowedApplicationList = null;

  public AuthenticationModel token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public AuthenticationModel allowedApplicationList(List<AllowedApplicationModel> allowedApplicationList) {
    this.allowedApplicationList = allowedApplicationList;
    return this;
  }

  public AuthenticationModel addAllowedApplicationListItem(AllowedApplicationModel allowedApplicationListItem) {
    if (this.allowedApplicationList == null) {
      this.allowedApplicationList = new ArrayList<AllowedApplicationModel>();
    }
    this.allowedApplicationList.add(allowedApplicationListItem);
    return this;
  }

  /**
   * Get allowedApplicationList
   * @return allowedApplicationList
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<AllowedApplicationModel> getAllowedApplicationList() {
    return allowedApplicationList;
  }

  public void setAllowedApplicationList(List<AllowedApplicationModel> allowedApplicationList) {
    this.allowedApplicationList = allowedApplicationList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthenticationModel authentication = (AuthenticationModel) o;
    return Objects.equals(this.token, authentication.token) &&
        Objects.equals(this.allowedApplicationList, authentication.allowedApplicationList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, allowedApplicationList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthenticationModel {\n");
    
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    allowedApplicationList: ").append(toIndentedString(allowedApplicationList)).append("\n");
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

