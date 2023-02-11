package org.hhschool.todolist.dto;

import jakarta.validation.constraints.NotEmpty;

public class TodoItemDescriptionDto {
  @NotEmpty
  private String description;

  public String getDescription() {
    return description;
  }
}
