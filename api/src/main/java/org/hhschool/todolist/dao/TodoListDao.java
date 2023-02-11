package org.hhschool.todolist.dao;

import org.hhschool.todolist.entity.TodoItem;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class TodoListDao {
  private final List<TodoItem> todos = new LinkedList<>();

  public void save(TodoItem item) {
    todos.add(item);
  }

  public Optional<TodoItem> get(Long id) {
    return todos.stream()
      .filter(item -> Objects.equals(item.getId(), id))
      .findFirst();
  }

  public List<TodoItem> all() {
    return todos;
  }

  public Optional<TodoItem> update(Long id, TodoItem newItem) {
    return get(id).map(item -> {
      item.setDescription(newItem.getDescription());
      item.setStatus(newItem.getStatus());
      return item;
    });
  }

  public void delete(Long id) {
    todos.removeIf(item -> Objects.equals(item.getId(), id));
  }

  public void deleteAll() {
    todos.clear();
  }
}
