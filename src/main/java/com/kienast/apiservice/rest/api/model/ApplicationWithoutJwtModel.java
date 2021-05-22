package com.kienast.apiservice.rest.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ApplicationWithoutJwtModel
 */

public class ApplicationWithoutJwtModel   {
  @JsonProperty("appname")
  private String appname;

  @JsonProperty("url")
  private String url;

  @JsonProperty("cssClasses")
  private String cssClasses;

  @JsonProperty("allowedUsers")
  @Valid
  private List<String> allowedUsers = null;

  public ApplicationWithoutJwtModel appname(String appname) {
    this.appname = appname;
    return this;
  }

  /**
   * Get appname
   * @return appname
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getAppname() {
    return appname;
  }

  public void setAppname(String appname) {
    this.appname = appname;
  }

  public ApplicationWithoutJwtModel url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   * @return url
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ApplicationWithoutJwtModel cssClasses(String cssClasses) {
    this.cssClasses = cssClasses;
    return this;
  }

  /**
   * Get cssClasses
   * @return cssClasses
  */
  @ApiModelProperty(value = "")


  public String getCssClasses() {
    return cssClasses;
  }

  public void setCssClasses(String cssClasses) {
    this.cssClasses = cssClasses;
  }

  public ApplicationWithoutJwtModel allowedUsers(List<String> allowedUsers) {
    this.allowedUsers = allowedUsers;
    return this;
  }

  public ApplicationWithoutJwtModel addAllowedUsersItem(String allowedUsersItem) {
    if (this.allowedUsers == null) {
      this.allowedUsers = new ArrayList<String>();
    }
    this.allowedUsers.add(allowedUsersItem);
    return this;
  }

  /**
   * Get allowedUsers
   * @return allowedUsers
  */
  @ApiModelProperty(value = "")


  public List<String> getAllowedUsers() {
    return allowedUsers;
  }

  public void setAllowedUsers(List<String> allowedUsers) {
    this.allowedUsers = allowedUsers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationWithoutJwtModel applicationWithoutJwt = (ApplicationWithoutJwtModel) o;
    return Objects.equals(this.appname, applicationWithoutJwt.appname) &&
        Objects.equals(this.url, applicationWithoutJwt.url) &&
        Objects.equals(this.cssClasses, applicationWithoutJwt.cssClasses) &&
        Objects.equals(this.allowedUsers, applicationWithoutJwt.allowedUsers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appname, url, cssClasses, allowedUsers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApplicationWithoutJwtModel {\n");
    
    sb.append("    appname: ").append(toIndentedString(appname)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    cssClasses: ").append(toIndentedString(cssClasses)).append("\n");
    sb.append("    allowedUsers: ").append(toIndentedString(allowedUsers)).append("\n");
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

