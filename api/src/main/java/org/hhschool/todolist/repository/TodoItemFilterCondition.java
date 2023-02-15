package org.hhschool.todolist.repository;

public record TodoItemFilterCondition(
  String title,
  Boolean completed)
{ }
