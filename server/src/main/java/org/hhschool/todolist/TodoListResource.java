package org.hhschool.todolist;

import org.hhschool.todolist.todoitem.TodoItemQueryParams;
import org.hhschool.todolist.todoitem.TodoItem;
import org.hhschool.todolist.todoitem.TodoItemConverter;
import org.hhschool.todolist.todoitem.TodoItemDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.util.stream.StreamSupport;

@Controller
@RequestMapping(path = "/todolist")
@CrossOrigin
public class TodoListResource {
  private final TodoListService todoListService;

  @Autowired
  public TodoListResource(TodoListService todoListService) {
    this.todoListService = todoListService;
  }

  @GetMapping("/items")
  @ResponseBody
  public Iterable<TodoItemDto> findAllItems(
    @RequestParam(required = false) Long id,
    @RequestParam(required = false) String title,
    @RequestParam(required = false) Boolean completed
  ) {
    TodoItemQueryParams queryParams = new TodoItemQueryParams(id, title, completed);
    Iterable<TodoItem> items = todoListService.findAllItems(queryParams);
    return StreamSupport.stream(items.spliterator(), false)
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
  public TodoItemDto replaceItem(@Valid @RequestBody TodoItemDto itemDto) {
    TodoItem replacedItem = todoListService.replaceItem(TodoItemConverter.fromDto(itemDto));
    return TodoItemConverter.toDto(replacedItem);
  }

  @PatchMapping("/items/{id}")
  @ResponseBody
  public TodoItemDto updateItem(@RequestBody TodoItemDto itemDto, @PathVariable Long id) {
    TodoItem item = TodoItemConverter.fromDto(itemDto);
    return todoListService.updateItem(item, id)
      .map(TodoItemConverter::toDto)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
