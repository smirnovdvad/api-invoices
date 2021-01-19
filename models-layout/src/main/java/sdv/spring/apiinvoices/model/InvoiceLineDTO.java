package sdv.spring.apiinvoices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceLineDTO {

    @JsonProperty("item_number")
    private String linenumber;
    @JsonProperty("good_description")
    private GoodDTO good;
    @JsonProperty("good_quantity")
    private BigDecimal quantity;
    @JsonProperty("amount_per_one_good")
    private BigDecimal baseamount;
    @JsonProperty("currency_iso_code")
    private Currency curr;

}
