package org.hhschool.todolist;

import org.hhschool.todolist.todoitem.TodoItemQueryParams;
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

  public Iterable<TodoItem> findAllItems(TodoItemQueryParams queryParams) {
    return todoListDao.findAllItems(queryParams);
  }

  public TodoItem saveItem(TodoItem item) {
    return todoListDao.saveItem(item);
  }

  public TodoItem replaceItem(TodoItem item) {
    return todoListDao.replaceItem(item);
  }

  public Optional<TodoItem> updateItem(TodoItem item, Long id) {
    return todoListDao.updateItem(item, id);
  }

  public void deleteItem(Long id) {
    todoListDao.deleteItem(id);
  }

  public void deleteAllItems() {
    todoListDao.deleteAllItems();
  }
}
