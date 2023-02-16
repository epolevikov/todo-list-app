package org.hhschool.todolist.repository;

import org.hhschool.todolist.todoitem.TodoItem;
import org.hhschool.todolist.todoitem.TodoItemQueryParams;

public interface TodoItemRepositoryCustom {
  Iterable<TodoItem> findAllItems(TodoItemQueryParams filterCondition);
}
