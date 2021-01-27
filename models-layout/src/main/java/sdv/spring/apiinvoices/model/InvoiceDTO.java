package sdv.spring.apiinvoices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    private String number;

    @JsonProperty("issue_date")
    private LocalDate date;

    @JsonProperty("company_issuer")
    private CompanyDTO companyissuer;

    @JsonProperty("company_receiver")
    private CompanyDTO companyreceiver;

    @JsonProperty("paymentmeans")
    private Set<PaymentMeanDTO> paymentmeans;

    @JsonProperty("items")
    private Set<InvoiceLineDTO> invoicelines;


}
