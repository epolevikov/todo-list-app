/*jshint eqeqeq:false */
(function (window) {
    'use strict';

    /**
     * Creates a new client side storage object and will create an empty
     * collection if no collection already exists.
     *
     * @param {function} callback Our fake DB uses callbacks because in
     * real life you probably would be making AJAX calls
     */
    function Store(backendUrl, callback) {
        callback = callback || function () {};
        this._backendUrl = backendUrl;
        this._todos = [];
        var self = this;

        const xhttp = new XMLHttpRequest();
        xhttp.onload = function() {
            self._todos = JSON.parse(xhttp.responseText);
            callback.call(self, self._todos); 
        }
        xhttp.open("GET", this._backendUrl + "/items", false);
        xhttp.send();
    }

    /**
     * Finds items based on a query given as a JS object
     *
     * @param {object} query The query to match against (i.e. {foo: 'bar'})
     * @param {function} callback	 The callback to fire when the query has
     * completed running
     *
     * @example
     * db.find({foo: 'bar', hello: 'world'}, function (data) {
     *	 // data will return any items that have foo: bar and
     *	 // hello: world in their properties
     * });
     */
    Store.prototype.find = function (query, callback) {
        if (!callback) {
            return;
        }

        callback.call(this, this._todos.filter(function (todo) {
            for (var q in query) {
                if (query[q] !== todo[q]) {
                    return false;
                }
            }
            return true;
        }));
    };

    /**
     * Will retrieve all data from the collection
     *
     * @param {function} callback The callback to fire upon retrieving data
     */
    Store.prototype.findAll = function (callback) {
        callback = callback || function () {};
        callback.call(this, this._todos);
    };

    /**
     * Will save the given data to the DB. If no item exists it will create a new
     * item, otherwise it'll simply update an existing item's properties
     *
     * @param {object} updateData The data to save back into the DB
     * @param {function} callback The callback to fire after saving
     * @param {number} id An optional param to enter an ID of an item to update
     */
    Store.prototype.save = function (updateData, callback, id) {
        callback = callback || function() {};

        const xhttp = new XMLHttpRequest();
        xhttp.open("PUT", this._backendUrl + "/items", true);
        xhttp.setRequestHeader('Content-type', 'application/json');

        // If an ID was actually given, find the item and update each property
        if (id) {
            for (var i = 0; i < this._todos.length; i++) {
                if (this._todos[i].id === id) {
                    for (var key in updateData) {
                        this._todos[i][key] = updateData[key];
                    }
                    xhttp.send(JSON.stringify(this._todos[i]));
                    break;
                }
            }

            // localStorage.setItem(this._dbName, JSON.stringify(todos));
            callback.call(this, this._todos);
        } else {
            // Generate an ID
            updateData.id = new Date().getTime();

            this._todos.push(updateData);
            xhttp.send(JSON.stringify(updateData));
            callback.call(this, [updateData]);
        }
    };

    /**
     * Will remove an item from the Store based on its ID
     *
     * @param {number} id The ID of the item you want to remove
     * @param {function} callback The callback to fire after saving
     */
    Store.prototype.remove = function (id, callback) {
        for (var i = 0; i < this._todos.length; i++) {
            if (this._todos[i].id == id) {
                this._todos.splice(i, 1);

                const xhttp = new XMLHttpRequest();
                xhttp.open("DELETE", this._backendUrl + '/items/' + id, true);
                xhttp.send();

                break;
            }
        }

        callback.call(this, this._todos);
    };

    /**
     * Will drop all storage and start fresh
     *
     * @param {function} callback The callback to fire after dropping the data
     */
    Store.prototype.drop = function (callback) {
        this._todos = [];
        const xhttp = new XMLHttpRequest();
        xhttp.open("DELETE", this._backendUrl + "/items", true);
        xhttp.send();
        callback.call(this, this._todos);
    };

    // Export to window
    window.app = window.app || {};
    window.app.Store = Store;
})(window);
