package org.hhschool.todolist.todoitem;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TodoItemDto {
  @NotNull
  private final Long id;
  @NotEmpty
  private final String title;
  @NotNull
  private final Boolean completed;

  public TodoItemDto(Long id, String title, Boolean isCompleted) {
    this.id = id;
    this.title = title;
    this.completed = isCompleted;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Boolean isCompleted() {
    return completed;
  }
}
