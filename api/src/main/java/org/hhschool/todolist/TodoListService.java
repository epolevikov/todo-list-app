package org.hhschool.todolist;

import org.hhschool.todolist.repository.TodoItemFilterCondition;
import org.hhschool.todolist.todoitem.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TodoListService {
  @Autowired
  private TodoListDao todoListDao;

  public Optional<TodoItem> findItemById(Long id) {
    return todoListDao.findItemById(id);
  }

  public Iterable<TodoItem> findAllItems(TodoItemFilterCondition filterCondition) {
    return todoListDao.findAllItems(filterCondition);
  }

  public TodoItem saveItem(TodoItem item) {
    return todoListDao.saveItem(item);
  }

  public TodoItem updateItem(TodoItem item) {
    return todoListDao.updateItem(item);
  }

  public void deleteItem(Long id) {
    todoListDao.deleteItem(id);
  }

  public void deleteAllItems() {
    todoListDao.deleteAllItems();
  }
}
