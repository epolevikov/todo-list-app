package org.hhschool.todolist.todoitem;

public class TodoItemConverter {
  public static TodoItemDto toDto(TodoItem item) {
    return new TodoItemDto(
      item.getId(),
      item.getTitle(),
      item.isCompleted());
  }

  public static TodoItem fromDto(TodoItemDto itemDto) {
    return new TodoItem(
      itemDto.getId(),
      itemDto.getTitle(),
      itemDto.isCompleted());
  }
}
