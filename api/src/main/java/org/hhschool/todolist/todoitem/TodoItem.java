package org.hhschool.todolist.todoitem;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TodoItem {
  @Id
  private Long id;
  private String title;
  private Boolean completed;

  public TodoItem() {}

  public TodoItem(Long id, String title, Boolean isCompleted) {
    this.id = id;
    this.title = title;
    this.completed = isCompleted;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean isCompleted) {
    this.completed = isCompleted;
  }
}
