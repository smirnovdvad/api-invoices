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
public class GoodDTO {
    private String name;
    @JsonProperty("unit_of_measure")
    private UnitOfMeasureDTO uom;
}
