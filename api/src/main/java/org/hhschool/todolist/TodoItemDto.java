package org.hhschool.todolist;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TodoItemDto {
  @NotNull
  private final Long id;
  @NotEmpty
  private final String description;
  @NotNull
  private final Boolean completed;

  public TodoItemDto(Long id, String description, Boolean isCompleted) {
    this.id = id;
    this.description = description;
    this.completed = isCompleted;
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public boolean isCompleted() {
    return completed;
  }
}
