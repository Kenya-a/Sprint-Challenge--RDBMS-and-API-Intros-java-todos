package com.lambdaschool.javatodos.controller;


import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//Put!!

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    // localhost:2018/todos/todoid/{todoid}
    @PutMapping(value = "/todoid/{todoid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> updateTodo(@Valid
                                        @RequestBody Todo todo,
                                        @PathVariable long todoid) {
        todo = todoService.update(todo, todoid);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
}