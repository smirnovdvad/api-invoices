package sdv.spring.apiinvoices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReversionDTO {

    @JsonProperty("invoice_number")
    private String invoiceNumber;

    @JsonProperty("company_issuer")
    private CompanyDTO companyissuer;

}
