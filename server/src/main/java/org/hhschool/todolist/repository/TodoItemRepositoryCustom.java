package org.hhschool.todolist.repository;

import org.hhschool.todolist.todoitem.TodoItem;
import org.hhschool.todolist.todoitem.TodoItemFilter;

public interface TodoItemRepositoryCustom {
  Iterable<TodoItem> findAllItems(TodoItemFilter todoItemFilter);
}
