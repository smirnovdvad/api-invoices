package sdv.spring.apiinvoices.controllers.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sdv.spring.apiinvoices.model.security.RoleDTO;
import sdv.spring.apiinvoices.services.security.RoleService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE , headers = "content-type=application/json")
    public Set<RoleDTO> getAllJson(){
        return roleService.getAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE , headers = "content-type=application/json")
    public RoleDTO postRoleJson(@RequestBody RoleDTO roleDTO){
        return roleService.postRole(roleDTO);
    }
}
