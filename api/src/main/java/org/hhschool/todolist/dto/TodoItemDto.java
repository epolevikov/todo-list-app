package org.hhschool.todolist.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class TodoItemDto {
  @NotNull private final Long id;
  @NotEmpty private final String description;

  @NotNull @Pattern(regexp = "active|completed")
  private final String status;

  public TodoItemDto(Long id, String description, String status) {
    this.id = id;
    this.description = description;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public String getStatus() {
    return status;
  }
}
