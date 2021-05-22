package com.kienast.apiservice.rest.api.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * TokenVerifiyResponseModel
 */

public class TokenVerifiyResponseModel   {
  @JsonProperty("mfaNeeded")
  private Boolean mfaNeeded;

  /**
   * Gets or Sets mfaAction
   */
  public enum MfaActionEnum {
    SETUP("setup"),
    
    VERIFIY("verifiy");

    private String value;

    MfaActionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static MfaActionEnum fromValue(String text) {
      for (MfaActionEnum b : MfaActionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
  }

  @JsonProperty("mfaAction")
  private MfaActionEnum mfaAction;

  public TokenVerifiyResponseModel mfaNeeded(Boolean mfaNeeded) {
    this.mfaNeeded = mfaNeeded;
    return this;
  }

  /**
   * Get mfaNeeded
   * @return mfaNeeded
  */
  @ApiModelProperty(value = "")


  public Boolean getMfaNeeded() {
    return mfaNeeded;
  }

  public void setMfaNeeded(Boolean mfaNeeded) {
    this.mfaNeeded = mfaNeeded;
  }

  public TokenVerifiyResponseModel mfaAction(MfaActionEnum mfaAction) {
    this.mfaAction = mfaAction;
    return this;
  }

  /**
   * Get mfaAction
   * @return mfaAction
  */
  @ApiModelProperty(value = "")


  public MfaActionEnum getMfaAction() {
    return mfaAction;
  }

  public void setMfaAction(MfaActionEnum mfaAction) {
    this.mfaAction = mfaAction;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenVerifiyResponseModel tokenVerifiyResponse = (TokenVerifiyResponseModel) o;
    return Objects.equals(this.mfaNeeded, tokenVerifiyResponse.mfaNeeded) &&
        Objects.equals(this.mfaAction, tokenVerifiyResponse.mfaAction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mfaNeeded, mfaAction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenVerifiyResponseModel {\n");
    
    sb.append("    mfaNeeded: ").append(toIndentedString(mfaNeeded)).append("\n");
    sb.append("    mfaAction: ").append(toIndentedString(mfaAction)).append("\n");
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

