package sdv.spring.apiinvoices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnitOfMeasureDTO {

    @JsonProperty("iso_code")
    private String name;
    @JsonProperty("iso_measure")
    private String description;
}
