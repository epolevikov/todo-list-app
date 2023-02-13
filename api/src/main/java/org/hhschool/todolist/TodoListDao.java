package org.hhschool.todolist;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class TodoListDao {
  private final List<TodoItem> todos = new LinkedList<>();

  public List<TodoItem> all() {
    return todos;
  }

  public Optional<TodoItem> get(Long id) {
    return todos.stream()
      .filter(item -> Objects.equals(item.getId(), id))
      .findFirst();
  }

  public void save(TodoItem item) {
    todos.add(item);
  }

  public TodoItem update(TodoItem newItem) {
    return get(newItem.getId()).map(item -> {
      item.setDescription(newItem.getDescription());
      item.setCompleted(newItem.isCompleted());
      return item;
    }).orElseGet(() -> {
      save(newItem);
      return newItem;
    });
  }

  public void delete(Long id) {
    todos.removeIf(item -> Objects.equals(item.getId(), id));
  }

  public void deleteAll() {
    todos.clear();
  }
}
