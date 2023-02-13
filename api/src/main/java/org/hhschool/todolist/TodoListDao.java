package org.hhschool.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TodoListDao {
  //private final List<TodoItem> todos = Collections.synchronizedList(new LinkedList<>());
  @Autowired
  private TodoItemRepository todoItemRepository;

  public Iterable<TodoItem> all() {
    return todoItemRepository.findAll();
    //return todos;
  }

  public Optional<TodoItem> get(Long id) {
    return todoItemRepository.findById(id);
    /*
    return todos.stream()
      .filter(item -> Objects.equals(item.getId(), id))
      .findFirst(); */
  }

  public TodoItem save(TodoItem item) {
    return todoItemRepository.save(item);
    //todos.add(item);
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
    //todos.removeIf(item -> Objects.equals(item.getId(), id));
  }

  public void deleteAll() {
    todoItemRepository.deleteAll();
  }
}
