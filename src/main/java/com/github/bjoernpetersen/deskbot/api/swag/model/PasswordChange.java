/*
 * JMusicBot
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 0.8.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.github.bjoernpetersen.deskbot.api.swag.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * PasswordChange
 */

public class PasswordChange {

  @JsonProperty("oldPassword")
  private String oldPassword = null;

  @JsonProperty("newPassword")
  private String newPassword = null;

  public PasswordChange oldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
    return this;
  }

  /**
   * The old password. Only required if the user is no guest.
   *
   * @return oldPassword
   **/
  @JsonProperty("oldPassword")
  @ApiModelProperty(value = "The old password. Only required if the user is no guest.")
  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public PasswordChange newPassword(String newPassword) {
    this.newPassword = newPassword;
    return this;
  }

  /**
   * The new password.
   *
   * @return newPassword
   **/
  @JsonProperty("newPassword")
  @ApiModelProperty(required = true, value = "The new password.")
  @NotNull
  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PasswordChange passwordChange = (PasswordChange) o;
    return Objects.equals(this.oldPassword, passwordChange.oldPassword) &&
        Objects.equals(this.newPassword, passwordChange.newPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(oldPassword, newPassword);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PasswordChange {\n");

    sb.append("    oldPassword: ").append(toIndentedString(oldPassword)).append("\n");
    sb.append("    newPassword: ").append(toIndentedString(newPassword)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first
   * line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
