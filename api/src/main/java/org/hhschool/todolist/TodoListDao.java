package org.hhschool.todolist;

import org.hhschool.todolist.repository.TodoItemFilterCondition;
import org.hhschool.todolist.todoitem.TodoItem;
import org.hhschool.todolist.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TodoListDao {
  @Autowired
  private TodoItemRepository todoItemRepository;

  public Iterable<TodoItem> findAllItems(TodoItemFilterCondition filterCondition) {
    return (filterCondition == null) ?
      todoItemRepository.findAll() :
      todoItemRepository.findAllItems(filterCondition);
  }

  public Optional<TodoItem> findItemById(Long id) {
    return todoItemRepository.findById(id);
  }

  public TodoItem saveItem(TodoItem item) {
    return todoItemRepository.save(item);
  }

  public TodoItem updateItem(TodoItem newItem) {
    return findItemById(newItem.getId()).map(item -> {
      item.setTitle(newItem.getTitle());
      item.setCompleted(newItem.isCompleted());
      return saveItem(item);
    }).orElseGet(() -> saveItem(newItem));
  }

  public void deleteItem(Long id) {
    todoItemRepository.deleteById(id);
  }

  public void deleteAllItems() {
    todoItemRepository.deleteAll();
  }
}
