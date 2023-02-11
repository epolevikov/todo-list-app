package org.hhschool.todolist.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.hhschool.todolist.converter.TodoItemConverter;
import org.hhschool.todolist.dao.TodoListDao;
import org.hhschool.todolist.dto.TodoItemDescriptionDto;
import org.hhschool.todolist.dto.TodoItemDto;
import org.hhschool.todolist.dto.TodoItemStatusDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping(path = "/todolist")
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

  @PutMapping("/items/{id}/description")
  @ResponseBody
  public TodoItemDto updateItemDescription(
    @Valid @RequestBody TodoItemDescriptionDto descriptionDto,
    @PathVariable Long id) {
    return todoListDao.get(id).map(item -> {
      item.setDescription(descriptionDto.getDescription());
      return TodoItemConverter.toDto(item);
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PutMapping("/items/{id}/status")
  @ResponseBody
  public TodoItemDto updateItemStatus(
    @Valid @RequestBody TodoItemStatusDto statusDto,
    @PathVariable Long id) {
    return todoListDao.get(id).map(item -> {
      item.setStatus(statusDto.getStatus());
      return TodoItemConverter.toDto(item);
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/items/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    todoListDao.delete(id);
  }

  @DeleteMapping("/items")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteAll(@PathVariable Long id) {
    todoListDao.deleteAll();
  }
}
