package com.lambdaschool.javatodos.controller;

import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.models.User;
import com.lambdaschool.javatodos.service.TodoService;
import com.lambdaschool.javatodos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

  @Autowired
    private TodoService todoService;

    //Get
    //localhost:2018/users/original

    @GetMapping(value = "/original", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(Authentication authentication)
    {
        return new ResponseEntity<>(userService.findUserByName(authentication.getName()), HttpStatus.OK);
    }

    //Post
    @PostMapping(value = "/", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid
                                        @RequestBody User newuser) throws URISyntaxException
    {
        newuser =  userService.save(newuser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //Adds Todo
    //localhost:2018/users/todo/{userid}

    @PostMapping(value="/todo/{userid}",consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addTodo(@Valid
                                     @RequestBody Todo todo,
                                     @PathVariable long userid){
        todo.setUser(userService.findUserById(userid));
        todo=todoService.save(todo);

        return new ResponseEntity<>(todo,HttpStatus.CREATED);
    }

    @DeleteMapping("/users/userid/{userid}")
    public ResponseEntity<?> removeUser(@PathVariable long userid){
        userService.delete(userid);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}