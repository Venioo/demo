package com.asseco.demo.user;

import com.asseco.demo.common.Chunk;
import com.asseco.demo.user.dto.UserDto;
import com.asseco.demo.user.form.UserCreateForm;
import com.asseco.demo.user.search.UserSearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.asseco.demo.user.UserUris.*;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @PostMapping(ADD_USER)
    @Operation(summary = "Adds user")
    public void addUser(@RequestBody @Valid UserCreateForm form) {
        userService.addUser(form);
    }

    @GetMapping(GET_USERS)
    @Operation(summary = "Returns all users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(FIND_USERS)
    @Operation(summary = "Returns chunk of users filtered by search criteria")
    public Chunk<UserDto> findUsers(@RequestBody @Valid UserSearchCriteria searchCriteria) {
        return userService.findUsers(searchCriteria);
    }

    @GetMapping(GET_USERS_FILE)
    @Operation(summary = "Downloads file with all users")
    public ResponseEntity<ByteArrayResource> getUsersFile() {
        return userService.getUsersFile();
    }

    @GetMapping(GENERATE_USERS)
    @Operation(summary = "Generates users")
    public void generateUsers() {
        userService.generateUsers();
    }

}
