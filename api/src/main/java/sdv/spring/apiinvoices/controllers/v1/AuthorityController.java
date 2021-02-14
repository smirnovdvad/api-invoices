package sdv.spring.apiinvoices.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sdv.spring.apiinvoices.model.security.AuthorityDTO;
import sdv.spring.apiinvoices.services.security.AuthorityService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE , headers = "content-type=application/json")
    public ResponseEntity<Object> getAllAuthorityJson(){
        return new ResponseEntity<>(authorityService.getAll(),
                HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE,
            headers="content-type=application/json")
    public ResponseEntity<Object> postAuthorityJson(@RequestBody AuthorityDTO authorityDTO){
        return new ResponseEntity<>(
                authorityService.postAuthority(authorityDTO), HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/multiple" , consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE,
            headers="content-type=application/json")
    public ResponseEntity<Void> postAuthoritiesJson(@RequestBody Set<AuthorityDTO> authorities){
        authorityService.postAuthorirties(authorities);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
