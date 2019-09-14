package com.lambdaschool.javatodos.repository;

import com.lambdaschool.javatodos.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long>
{

} 