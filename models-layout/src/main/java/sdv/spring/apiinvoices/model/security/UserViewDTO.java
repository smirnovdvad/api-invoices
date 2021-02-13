package sdv.spring.apiinvoices.model.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sdv.spring.apiinvoices.domain.security.Role;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserViewDTO {

    @JsonProperty("user_name")
    private String username;

    @JsonProperty("assigned_roles")
    private Set<RoleDTO> roles;

    @JsonProperty("is_account_expired")
    private Boolean accountIsExpired;

    @JsonProperty("is_account_locked")
    private Boolean accountIsLocked;

    @JsonProperty("is_credentials_expired")
    private Boolean credentialsIsExpired;

    @JsonProperty("is_enabled")
    private Boolean enabled;
}
