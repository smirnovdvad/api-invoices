package sdv.spring.apiinvoices.model.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sdv.spring.apiinvoices.domain.security.Authority;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    @JsonProperty("role_name")
    private String name;

    @JsonProperty("assigned_authorities")
    private Set<AuthorityDTO> authorities;
}
