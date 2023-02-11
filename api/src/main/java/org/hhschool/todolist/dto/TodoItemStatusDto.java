package org.hhschool.todolist.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class TodoItemStatusDto {
  @NotNull
  @Pattern(regexp = "active|completed")
  private String status;

  public String getStatus() {
    return status;
  }
}
