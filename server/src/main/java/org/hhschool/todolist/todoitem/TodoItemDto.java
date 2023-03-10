package org.hhschool.todolist.todoitem;

public record TodoItemDto (
  Long id,
  String title,
  Boolean completed)
{ }
