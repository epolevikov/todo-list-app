package org.hhschool.todolist.todoitem;

public record TodoItemFilter(
  Long id,
  String title,
  Boolean completed)
{ }
