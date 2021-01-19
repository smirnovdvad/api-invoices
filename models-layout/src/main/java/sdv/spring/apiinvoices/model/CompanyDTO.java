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
}
