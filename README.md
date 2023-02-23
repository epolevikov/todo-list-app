# Todo List App

Web приложение, которое позволяет управлять списком задач: добавлять, редактировать, удалять, а также помечать задачи как выполненные. Todo List App состоит из следующих компонентов:

* **Application cервер**. Java приложение реализованное с помощью Spring. Предоставляет [HTTP API](#todo-list-api) для работы со списком задач.
* **База данных (PostgreSQL)**. Хранит элементы списка задач и их состояния.
* **Клиент**. Готовое [Vanilla JS приложение](https://github.com/tastejs/todomvc/tree/gh-pages/examples/vanillajs). В оригинальной версии для хранения элементов списка задач используется локальное хранилище (`localStorage`). В данном проекте для хранения состояния, обновления и поиска элементов списка используется [Todo List API](#todo-list-api).
* **Web сервер (Nginx)**. Входная точка для взаимодействия с приложением. В зависмости от указанного в запросе URL Nginx либо отдает HTML страницу с UI, либо проксирует запрос на application сервер.

## Как запустить

```
git clone https://github.com/epolevikov/todo-list-app && cd todo-list-app
docker compose up
```

С приложением можно взаимодействовать как через браузер, открыв в нем `localhost:80` (или просто `localhost`), так и с помощью [Todo List API](#todo-list-app).

## Todo List API

Предоставляет два эндпоинта:

* **/todolist/items** — для работы со всеми элементами списка задач.
* **/todolist/items/{id}** — для работы с конкретным элементом с указанным `id`.

Для первого эндпоинта доступны методы `GET`, `POST`, и `DELETE`. Для второго — `GET`, `PATCH`, `DELETE`. Ниже приведено подробное описание каждого из возможных запросов. 

### GET /todolist/items

Возвращает элементы списка задач. В строку запроса можно включить следующие необязательные параметры:

* **id (integer)** — идентификатор элемента списка.
* **title (string)** — название элемента списка.
* **completed (boolean)** — статус элемента списка. `true` для выполненных задач, `false` — если задача ещё не выполенена.

Если ни один из параметров не указан, то метод вернет все элементы списка. Если один или несколько параметров указаны, то метод вернет все элементы, соответствующие поля которых совпадают с указанными значениями параметров.

#### Пример запроса:

```
curl localhost/todolist/items?completed=false | json_pp
```

#### Пример ответа:

```
[
   {
      "completed" : false,
      "id" : 1,
      "title" : "wash dishes"
   },
   {
      "completed" : false,
      "id" : 2,
      "title" : "go to the dentist"
   },
   {
      "completed" : false,
      "id" : 3,
      "title" : "learn docker"
   }
]
```

### POST /todolist/items

Добавляет новый элемент в список задач. Название элемента должно содержаться в теле запроса.

#### Пример запроса:

```
curl -X POST localhost/todolist/items \
    -H "Content-Type: text/plain" \
    -d 'learn Spring'
```

#### Пример ответа:

```
{
   "completed" : false,
   "id" : 4,
   "title" : "learn Spring"
}
```

### DELETE /todolist/items

Удаляет все элементы списка задач.

### GET /todolist/items/{id}

Возвращает элемент с указанным `id`. Если элемента с указанным `id` не существует, возвращает `404 Not Found`.

#### Пример запроса:

```
curl localhost/todolist/items/4 | json_pp
```

#### Пример ответа:

```
{
   "completed" : false,
   "id" : 4,
   "title" : "learn Spring"
}
```

### PATCH /todolist/items/{id}

Обновляет элемент с указанным `id`. Если элемента с указанным `id` не существует, возвращает `404 Not Found`.

#### Пример запроса:

```
curl -X PATCH localhost/todolist/items/4 \
    -H "Content-Type: application/json" \
    -d '{"completed": true}'
```

#### Пример ответа:

```
{
   "completed" : true,
   "id" : 4,
   "title" : "learn Spring"
}
```

### DELETE /todolist/items/{id}

Удаляет элемент с указанным `id`.
