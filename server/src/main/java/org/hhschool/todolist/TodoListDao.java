package org.hhschool.todolist;

import org.hhschool.todolist.todoitem.TodoItemQueryParams;
import org.hhschool.todolist.todoitem.TodoItem;
import org.hhschool.todolist.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TodoListDao {
  @Autowired
  private TodoItemRepository todoItemRepository;

  public Iterable<TodoItem> findAllItems(TodoItemQueryParams queryParams) {
      return todoItemRepository.findAllItems(queryParams);
  }

  public Optional<TodoItem> findItemById(Long id) {
    return todoItemRepository.findById(id);
  }

  public TodoItem saveItem(TodoItem item) {
    return todoItemRepository.save(item);
  }

  public TodoItem replaceItem(TodoItem newItem) {
    return findItemById(newItem.getId()).map(item -> {
      item.setTitle(newItem.getTitle());
      item.setCompleted(newItem.isCompleted());
      return saveItem(item);
    }).orElseGet(() -> saveItem(newItem));
  }

  public Optional<TodoItem> updateItem(TodoItem item, Long id) {
    return findItemById(id)
      .map(foundItem -> {
        if (item.getTitle() != null) {
          foundItem.setTitle(item.getTitle());
        }

        if (item.isCompleted() != null) {
          foundItem.setCompleted(item.isCompleted());
        }

        return saveItem(foundItem);
      }
    );
  }

  public void deleteItem(Long id) {
    todoItemRepository.deleteById(id);
  }

  public void deleteAllItems() {
    todoItemRepository.deleteAll();
  }
}
