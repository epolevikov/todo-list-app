package org.hhschool.todolist;

public class TodoItemConverter {
  public static TodoItemDto toDto(TodoItem item) {
    return new TodoItemDto(
      item.getId(),
      item.getDescription(),
      item.isCompleted());
  }

  public static TodoItem fromDto(TodoItemDto itemDto) {
    return new TodoItem(
      itemDto.getId(),
      itemDto.getDescription(),
      itemDto.isCompleted());
  }
}
