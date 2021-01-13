package com.apress.todo.repository;

import org.springframework.data.repository.CrudRepository;

import com.apress.todo.domain.ToDo;

public interface ToDoRepository_JPA extends CrudRepository<ToDo, String>{

}
