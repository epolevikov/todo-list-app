package org.hhschool.todolist;

import org.hhschool.todolist.todoitem.TodoItemDtoPartial;
import org.hhschool.todolist.todoitem.TodoItemQueryParams;
import org.hhschool.todolist.todoitem.TodoItem;
import org.hhschool.todolist.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TodoListService {
  private final TodoItemRepository todoItemRepository;

  @Autowired
  public TodoListService(TodoItemRepository todoItemRepository) {
    this.todoItemRepository = todoItemRepository;
  }

  public Iterable<TodoItem> findAllItems(TodoItemQueryParams queryParams) {
      return todoItemRepository.findAllItems(queryParams);
  }

  public Optional<TodoItem> findItemById(Long id) {
    return todoItemRepository.findById(id);
  }

  public TodoItem saveItem(String itemTitle) {
    return todoItemRepository.save(new TodoItem(itemTitle));
  }

  public Optional<TodoItem> updateItem(TodoItemDtoPartial itemDto, Long id) {
    return findItemById(id)
      .map(foundItem -> {
        Optional.ofNullable(itemDto.title())
          .ifPresent(foundItem::setTitle);

        Optional.ofNullable(itemDto.completed())
          .ifPresent(foundItem::setCompleted);

        return todoItemRepository.save(foundItem);
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
