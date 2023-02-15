package org.hhschool.todolist.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hhschool.todolist.todoitem.TodoItem;

import java.util.ArrayList;
import java.util.List;

public class TodoItemRepositoryCustomImpl implements TodoItemRepositoryCustom {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Iterable<TodoItem> findAllItems(TodoItemFilterCondition filterCondition) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<TodoItem> query = cb.createQuery(TodoItem.class);
    Root<TodoItem> root = query.from(TodoItem.class);

    List<Predicate> predicates = new ArrayList<>();

    if (filterCondition.completed() != null) {
      predicates.add(cb.equal(root.get("completed"), filterCondition.completed()));
    }

    if (filterCondition.title() != null) {
      predicates.add(cb.like(root.get("title"), filterCondition.title()));
    }

    query.select(root);
    if (!predicates.isEmpty()) {
      query.where(predicates.toArray(new Predicate[0]));
    }

    return entityManager.createQuery(query).getResultList();
  }
}
