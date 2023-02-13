package org.hhschool.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TodoListDao {
  @Autowired
  private TodoItemRepository todoItemRepository;

  public Iterable<TodoItem> all() {
    return todoItemRepository.findAll();
  }

  public Optional<TodoItem> get(Long id) {
    return todoItemRepository.findById(id);
  }

  public TodoItem save(TodoItem item) {
    return todoItemRepository.save(item);
  }

  public TodoItem update(TodoItem newItem) {
    return get(newItem.getId()).map(item -> {
      item.setTitle(newItem.getTitle());
      item.setCompleted(newItem.isCompleted());
      return save(item);
    }).orElseGet(() -> save(newItem));
  }

  public void delete(Long id) {
    todoItemRepository.deleteById(id);
  }

  public void deleteAll() {
    todoItemRepository.deleteAll();
  }
}
