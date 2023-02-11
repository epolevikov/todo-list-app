package org.hhschool.todolist.entity;

public class TodoItem {
  private final Long id;
  private String description;
  private TodoItemStatus status;

  public TodoItem(Long id, String description, String status) {
    this.id = id;
    this.description = description;
    setStatus(status);
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TodoItemStatus getStatus() {
    return status;
  }

  public void setStatus(TodoItemStatus status) {
    this.status = status;
  }

  public void setStatus(String status) {
    switch (status) {
      case "active" -> this.status = TodoItemStatus.ACTIVE;
      case "completed" -> this.status = TodoItemStatus.COMPLETED;
    }
  }
}
