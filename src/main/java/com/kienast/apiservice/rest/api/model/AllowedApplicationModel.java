package com.kienast.apiservice.rest.api.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * AllowedApplicationModel
 */

public class AllowedApplicationModel   {
  @JsonProperty("id")
  private String id;

  @JsonProperty("appname")
  private String appname;

  @JsonProperty("url")
  private String url;

  @JsonProperty("cssClasses")
  private String cssClasses;

  public AllowedApplicationModel id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AllowedApplicationModel appname(String appname) {
    this.appname = appname;
    return this;
  }

  /**
   * Get appname
   * @return appname
  */
  @ApiModelProperty(value = "")


  public String getAppname() {
    return appname;
  }

  public void setAppname(String appname) {
    this.appname = appname;
  }

  public AllowedApplicationModel url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   * @return url
  */
  @ApiModelProperty(value = "")


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public AllowedApplicationModel cssClasses(String cssClasses) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AllowedApplicationModel allowedApplication = (AllowedApplicationModel) o;
    return Objects.equals(this.id, allowedApplication.id) &&
        Objects.equals(this.appname, allowedApplication.appname) &&
        Objects.equals(this.url, allowedApplication.url) &&
        Objects.equals(this.cssClasses, allowedApplication.cssClasses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, appname, url, cssClasses);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AllowedApplicationModel {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    appname: ").append(toIndentedString(appname)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    cssClasses: ").append(toIndentedString(cssClasses)).append("\n");
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

