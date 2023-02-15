package org.hhschool.todolist.repository;

import org.hhschool.todolist.todoitem.TodoItem;

public interface TodoItemRepositoryCustom {
  Iterable<TodoItem> findAllItems(TodoItemFilterCondition filterCondition);
}
