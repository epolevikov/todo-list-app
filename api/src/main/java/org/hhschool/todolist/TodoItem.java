package org.hhschool.todolist;

public class TodoItem {
  private final Long id;
  private String description;
  private Boolean completed;

  public TodoItem(Long id, String description, Boolean isCompleted) {
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

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean value) {
    this.completed = value;
  }
}
