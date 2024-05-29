package io.swagger.model;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Payment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-27T06:11:57.478186861Z[GMT]")


public class Payment  implements Serializable {
  @JsonProperty("payeeId")
  private UUID payeeId = null;

  @JsonProperty("PaymentMethodID")
  private UUID paymentMethodID = null;

  @JsonProperty("amount")
  private String amount = null;

  @JsonProperty("currency")
  private String currency = null;

  public Payment payeeId(UUID payeeId) {
    this.payeeId = payeeId;
    return this;
  }

  /**
   * Get payeeId
   * @return payeeId
   **/
  @Schema(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", description = "")
  
    @Valid
    public UUID getPayeeId() {
    return payeeId;
  }

  public void setPayeeId(UUID payeeId) {
    this.payeeId = payeeId;
  }

  public Payment paymentMethodID(UUID paymentMethodID) {
    this.paymentMethodID = paymentMethodID;
    return this;
  }

  /**
   * Get paymentMethodID
   * @return paymentMethodID
   **/
  @Schema(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", required = true, description = "")
      @NotNull

    @Valid
    public UUID getPaymentMethodID() {
    return paymentMethodID;
  }

  public void setPaymentMethodID(UUID paymentMethodID) {
    this.paymentMethodID = paymentMethodID;
  }

  public Payment amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
   **/
  @Schema(example = "100.45", required = true, description = "")
      @NotNull

    public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public Payment currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
   **/
  @Schema(example = "USD", required = true, description = "")
      @NotNull

    public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payment payment = (Payment) o;
    return Objects.equals(this.payeeId, payment.payeeId) &&
        Objects.equals(this.paymentMethodID, payment.paymentMethodID) &&
        Objects.equals(this.amount, payment.amount) &&
        Objects.equals(this.currency, payment.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(payeeId, paymentMethodID, amount, currency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payment {\n");
    
    sb.append("    payeeId: ").append(toIndentedString(payeeId)).append("\n");
    sb.append("    paymentMethodID: ").append(toIndentedString(paymentMethodID)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
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
