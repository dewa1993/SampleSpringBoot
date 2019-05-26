package rest.endpoint.user.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.endpoint.user.domain.User;
import rest.endpoint.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    @ApiOperation(
            value = "Get all users",
            notes = "Returns first N users specified by the size parameter with page offset specified by page parameter.",
            response = Page.class)
    public List<User> getUsers(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "50") int limit) {
        return userService.getAllUsers();
    }

    @ApiOperation(
            value = "Get user by id",
            notes = "Returns person for id specified.",
            response = User.class)
    @GetMapping(path = "/user/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.findOne(userId).get(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create new or update existing user",
            notes = "Creates new or updates existing user. Returns created/updated user with id.",
            response = User.class)
    @PostMapping(
            path = "/user",
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<User> createUser(@Valid @RequestBody User userRestRequest) {
        return new ResponseEntity<>(userService.save(userRestRequest), HttpStatus.OK);
    }


    @ApiOperation(
            value = "update existing user",
            notes = "updates existing user. Returns updated user with id.",
            response = User.class)
    @PutMapping(path = "/user")
    public User updateUser(@Valid @RequestBody User userRestRequest) {
        return userService.update(userRestRequest);
    }

    @ApiOperation(
            value = "Delete existing user",
            notes = "Deletes existing user. Returns deleted user with id.",
            response = User.class)
    @DeleteMapping(path = "/user")
    public String deleteUser(@Valid @RequestBody User userRestRequest) {
        userService.delete(userRestRequest);
        return "Deleted";
    }

}
