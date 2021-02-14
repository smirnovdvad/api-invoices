package sdv.spring.apiinvoices.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sdv.spring.apiinvoices.mapper.security.UserViewMapper;
import sdv.spring.apiinvoices.model.security.UserUpdateDTO;
import sdv.spring.apiinvoices.services.security.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserViewMapper userViewMapper = UserViewMapper.INSTANCE;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE , headers = "content-type=application/xml")
    public ResponseEntity<Object> getAllUsersXML(){
        return new ResponseEntity<>(
               userService.getAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE , headers = "content-type=application/json")
    public ResponseEntity<Object> getAllUsersJSON(){
        return new ResponseEntity<>(
                userService.getAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE , headers = "content-type=application/json")
    public ResponseEntity<Object> postUserJson(@RequestBody UserUpdateDTO userUpdateDTO){
        return new ResponseEntity<>(
                userService.postUser(userUpdateDTO),HttpStatus.OK
        );
    }
}
