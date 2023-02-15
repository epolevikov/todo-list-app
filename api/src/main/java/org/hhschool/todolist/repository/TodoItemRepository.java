package org.hhschool.todolist.repository;

import org.hhschool.todolist.todoitem.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItem, Long>, TodoItemRepositoryCustom {
}
