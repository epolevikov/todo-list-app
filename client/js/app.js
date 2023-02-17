/*global app, $on */
(function () {
    'use strict';

    /**
     * Sets up a brand new Todo list.
     */
    function Todo(todoApiRoot) {
        this.storage = new app.Store(todoApiRoot);
        this.model = new app.Model(this.storage);
        this.template = new app.Template();
        this.view = new app.View(this.template);
        this.controller = new app.Controller(this.model, this.view);
    }

    var todoApiRoot = "http://localhost/todolist";
    var todo = new Todo(todoApiRoot);

    function setView() {
        todo.controller.setView(document.location.hash);
    }
    $on(window, 'load', setView);
    $on(window, 'hashchange', setView);
})();
