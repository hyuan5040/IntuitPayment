package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PaymentMethod
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-27T06:11:57.478186861Z[GMT]")


public class PaymentMethod   {


  // todo : forgot type : such as visa, master, paypal etc

  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("PaymentMethodContent")
  private String paymentMethodContent = null;

  @JsonProperty("userid")
  private String userid = null;

  @JsonProperty("expireDate")
  private String expireDate = null;

  public PaymentMethod id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", description = "")
  
    @Valid
    public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public PaymentMethod paymentMethodContent(String paymentMethodContent) {
    this.paymentMethodContent = paymentMethodContent;
    return this;
  }

  /**
   * Get paymentMethodContent
   * @return paymentMethodContent
   **/
  @Schema(example = "Visa Card Number", description = "")
  
    public String getPaymentMethodContent() {
    return paymentMethodContent;
  }

  public void setPaymentMethodContent(String paymentMethodContent) {
    this.paymentMethodContent = paymentMethodContent;
  }

  public PaymentMethod userid(String userid) {
    this.userid = userid;
    return this;
  }

  /**
   * Get userid
   * @return userid
   **/
  @Schema(example = "uid001", description = "")
  
    public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public PaymentMethod expireDate(String expireDate) {
    this.expireDate = expireDate;
    return this;
  }

  /**
   * Get expireDate
   * @return expireDate
   **/
  @Schema(example = "20280909", description = "")
  
    public String getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(String expireDate) {
    this.expireDate = expireDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentMethod paymentMethod = (PaymentMethod) o;
    return Objects.equals(this.id, paymentMethod.id) &&
        Objects.equals(this.paymentMethodContent, paymentMethod.paymentMethodContent) &&
        Objects.equals(this.userid, paymentMethod.userid) &&
        Objects.equals(this.expireDate, paymentMethod.expireDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, paymentMethodContent, userid, expireDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentMethod {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    paymentMethodContent: ").append(toIndentedString(paymentMethodContent)).append("\n");
    sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
    sb.append("    expireDate: ").append(toIndentedString(expireDate)).append("\n");
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
