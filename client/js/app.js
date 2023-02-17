/*global app, $on */
(function () {
    'use strict';

    /**
     * Sets up a brand new Todo list.
     */
    function Todo(backendUrl) {
        this.storage = new app.Store(backendUrl);
        this.model = new app.Model(this.storage);
        this.template = new app.Template();
        this.view = new app.View(this.template);
        this.controller = new app.Controller(this.model, this.view);
    }

    var backendUrl = "http://localhost/todolist";
    var todo = new Todo(backendUrl);

    function setView() {
        todo.controller.setView(document.location.hash);
    }
    $on(window, 'load', setView);
    $on(window, 'hashchange', setView);
})();
