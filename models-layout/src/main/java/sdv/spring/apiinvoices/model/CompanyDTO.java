package sdv.spring.apiinvoices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sdv.spring.apiinvoices.domain.Company;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    @JsonProperty("company_registered_name")
    private String name;
    @JsonProperty("country_of_origin")
    private String country;
    @JsonProperty("full_address")
    private String address;
    @JsonProperty("official_email")
    private String email;
    private String phone;
    @JsonProperty("tax_id_number")
    private String tin;

    @Override
    public boolean equals(Object obj) {
        if (obj  instanceof CompanyDTO == false)
            return false;

        CompanyDTO companyToCompare = (CompanyDTO)  obj;
        if (this.getTin().equals(companyToCompare.getTin()))
            return true;
        else
            return false;

    }
}
