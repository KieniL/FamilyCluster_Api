package com.kienast.apiservice.rest.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * CategoryResponseModel
 */

public class CategoryResponseModel   {
  @JsonProperty("description")
  private String description;

  @JsonProperty("entries")
  @Valid
  private List<AmountEntryModel> entries = new ArrayList<AmountEntryModel>();

  public CategoryResponseModel description(String description) {
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

  public CategoryResponseModel entries(List<AmountEntryModel> entries) {
    this.entries = entries;
    return this;
  }

  public CategoryResponseModel addEntriesItem(AmountEntryModel entriesItem) {
    this.entries.add(entriesItem);
    return this;
  }

  /**
   * Get entries
   * @return entries
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<AmountEntryModel> getEntries() {
    return entries;
  }

  public void setEntries(List<AmountEntryModel> entries) {
    this.entries = entries;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CategoryResponseModel categoryResponse = (CategoryResponseModel) o;
    return Objects.equals(this.description, categoryResponse.description) &&
        Objects.equals(this.entries, categoryResponse.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, entries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategoryResponseModel {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    entries: ").append(toIndentedString(entries)).append("\n");
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

