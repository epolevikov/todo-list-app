package org.hhschool.todolist;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping(path = "/todolist")
@CrossOrigin
public class TodoListController {
  @Autowired
  private TodoListDao todoListDao;

  @GetMapping("/items")
  @ResponseBody
  public Iterable<TodoItemDto> all() {
    return todoListDao.all().stream()
      .map(TodoItemConverter::toDto)
      .toList();
  }

  @GetMapping("/items/{id}")
  @ResponseBody
  public TodoItemDto get(@PathVariable Long id) {
    return todoListDao.get(id)
      .map(TodoItemConverter::toDto)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/items")
  @ResponseStatus(value = HttpStatus.CREATED)
  public void save(@Valid @RequestBody TodoItemDto itemDto, HttpServletResponse response) {
    todoListDao.save(TodoItemConverter.fromDto(itemDto));

    String resourceLocation = ServletUriComponentsBuilder
      .fromCurrentRequestUri()
      .toUriString()
      .concat("/" + itemDto.getId());

    response.addHeader("Location", resourceLocation);
  }

  @PutMapping("/items")
  @ResponseBody
  public TodoItemDto update(@Valid @RequestBody TodoItemDto itemDto) {
    TodoItem updatedItem = todoListDao.update(TodoItemConverter.fromDto(itemDto));
    return TodoItemConverter.toDto(updatedItem);
  }

  @DeleteMapping("/items/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    todoListDao.delete(id);
  }

  @DeleteMapping("/items")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteAll() {
    todoListDao.deleteAll();
  }
}
