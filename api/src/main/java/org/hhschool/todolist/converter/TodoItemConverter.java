package org.hhschool.todolist.converter;

import org.hhschool.todolist.dto.TodoItemDto;
import org.hhschool.todolist.entity.TodoItem;

public class TodoItemConverter {
  public static TodoItemDto toDto(TodoItem item) {
    return new TodoItemDto(
      item.getId(),
      item.getDescription(),
      item.getStatus().toString().toLowerCase());
  }

  public static TodoItem fromDto(TodoItemDto itemDto) {
    return new TodoItem(
      itemDto.getId(),
      itemDto.getDescription(),
      itemDto.getStatus());
  }
}
