package org.hhschool.todolist;

import org.hhschool.todolist.repository.TodoItemFilterCondition;
import org.hhschool.todolist.todoitem.TodoItem;
import org.hhschool.todolist.todoitem.TodoItemConverter;
import org.hhschool.todolist.todoitem.TodoItemDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.util.stream.StreamSupport;

@Controller
@RequestMapping(path = "/todolist")
@CrossOrigin
public class TodoListResource {
  @Autowired
  private TodoListService todoListService;

  @GetMapping("/items")
  @ResponseBody
  public Iterable<TodoItemDto> findAllItems(@RequestBody(required = false) TodoItemFilterCondition filterCondition) {
    return StreamSupport.stream(todoListService.findAllItems(filterCondition).spliterator(), false)
      .map(TodoItemConverter::toDto)
      .toList();
  }

  @GetMapping("/items/{id}")
  @ResponseBody
  public TodoItemDto findItemById(@PathVariable Long id) {
    return todoListService.findItemById(id)
      .map(TodoItemConverter::toDto)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/items")
  @ResponseStatus(value = HttpStatus.CREATED)
  public void saveItem(@Valid @RequestBody TodoItemDto itemDto, HttpServletResponse response) {
    todoListService.saveItem(TodoItemConverter.fromDto(itemDto));

    String resourceLocation = ServletUriComponentsBuilder
      .fromCurrentRequestUri()
      .toUriString()
      .concat("/" + itemDto.getId());

    response.addHeader("Location", resourceLocation);
  }

  @PutMapping("/items")
  @ResponseBody
  public TodoItemDto updateItem(@Valid @RequestBody TodoItemDto itemDto) {
    TodoItem updatedItem = todoListService.updateItem(TodoItemConverter.fromDto(itemDto));
    return TodoItemConverter.toDto(updatedItem);
  }

  @DeleteMapping("/items/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteItem(@PathVariable Long id) {
    todoListService.deleteItem(id);
  }

  @DeleteMapping("/items")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteAllItems() {
    todoListService.deleteAllItems();
  }
}
