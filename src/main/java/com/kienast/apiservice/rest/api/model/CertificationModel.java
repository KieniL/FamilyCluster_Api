package com.kienast.apiservice.rest.api.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * CertificationModel
 */

public class CertificationModel   {
  @JsonProperty("shortname")
  private String shortname;

  @JsonProperty("description")
  private String description;

  @JsonProperty("dateFrom")
  private LocalDate dateFrom;

  @JsonProperty("dateTo")
  private LocalDate dateTo;

  public CertificationModel shortname(String shortname) {
    this.shortname = shortname;
    return this;
  }

  /**
   * Get shortname
   * @return shortname
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getShortname() {
    return shortname;
  }

  public void setShortname(String shortname) {
    this.shortname = shortname;
  }

  public CertificationModel description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CertificationModel dateFrom(LocalDate dateFrom) {
    this.dateFrom = dateFrom;
    return this;
  }

  /**
   * Get dateFrom
   * @return dateFrom
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public LocalDate getDateFrom() {
    return dateFrom;
  }

  public void setDateFrom(LocalDate dateFrom) {
    this.dateFrom = dateFrom;
  }

  public CertificationModel dateTo(LocalDate dateTo) {
    this.dateTo = dateTo;
    return this;
  }

  /**
   * Get dateTo
   * @return dateTo
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public LocalDate getDateTo() {
    return dateTo;
  }

  public void setDateTo(LocalDate dateTo) {
    this.dateTo = dateTo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CertificationModel certification = (CertificationModel) o;
    return Objects.equals(this.shortname, certification.shortname) &&
        Objects.equals(this.description, certification.description) &&
        Objects.equals(this.dateFrom, certification.dateFrom) &&
        Objects.equals(this.dateTo, certification.dateTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shortname, description, dateFrom, dateTo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CertificationModel {\n");
    
    sb.append("    shortname: ").append(toIndentedString(shortname)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    dateFrom: ").append(toIndentedString(dateFrom)).append("\n");
    sb.append("    dateTo: ").append(toIndentedString(dateTo)).append("\n");
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

