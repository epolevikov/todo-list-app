package org.hhschool.todolist.todoitem;

public record TodoItemQueryParams(
  Long id,
  String title,
  Boolean completed)
{ }
