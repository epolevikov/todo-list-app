/*jshint eqeqeq:false */
(function (window) {
    'use strict';

    function Store(backendUrl, callback) {
        callback = callback || function () {};
        this._backendUrl = backendUrl;

        const xhttp = new XMLHttpRequest();
        var self = this;

        xhttp.onload = function() {
            var todos = JSON.parse(xhttp.responseText);
            callback.call(self, todos); 
        }

        xhttp.open("GET", this._backendUrl + "/items", false);
        xhttp.send();
    }

    /**
     * Finds items based on a query given as a JS object
     *
     * @param {object} query The query to match against (i.e. {foo: 'bar'})
     * @param {function} callback The callback to fire when the query has
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

        var queryString = Object.keys(query).map(function(key) {
            return key + '=' + query[key]
        }).join('&');

        const xhttp = new XMLHttpRequest();
        var self = this;

        xhttp.onload = function() {
            var todos = JSON.parse(xhttp.responseText);
            callback.call(self, todos); 
        }
        
        xhttp.open("GET", this._backendUrl + "/items" + "?" + queryString, false);
        xhttp.send();
    };

    /**
     * Will retrieve all data from the collection
     *
     * @param {function} callback The callback to fire upon retrieving data
     */
    Store.prototype.findAll = function (callback) {
        callback = callback || function () {};

        const xhttp = new XMLHttpRequest();
        var self = this;

        xhttp.onload = function() {
            var todos = JSON.parse(xhttp.responseText);
            callback.call(self, todos); 
        }
        
        xhttp.open("GET", this._backendUrl + "/items", false);
        xhttp.send();
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
        var self = this;

        xhttp.onload = function() {
            if (id) {
                self.findAll(callback);
            } else {
                callback.call(self, [updateData]);
            }
        }

        if (id) {
            xhttp.open("PATCH", this._backendUrl + "/items/" + id, false);
        } else {
            updateData.id = new Date().getTime();
            xhttp.open("PUT", this._backendUrl + "/items", false);
        }

        xhttp.setRequestHeader('Content-type', 'application/json');
        xhttp.send(JSON.stringify(updateData));
    };

    /**
     * Will remove an item from the Store based on its ID
     *
     * @param {number} id The ID of the item you want to remove
     * @param {function} callback The callback to fire after saving
     */
    Store.prototype.remove = function (id, callback) {
        const xhttp = new XMLHttpRequest();
        var self = this;

        xhttp.onload = function() {
            self.findAll(callback);
        }

        xhttp.open("DELETE", this._backendUrl + '/items/' + id, false);
        xhttp.send();
    };

    /**
     * Will drop all storage and start fresh
     *
     * @param {function} callback The callback to fire after dropping the data
     */
    Store.prototype.drop = function (callback) {
        const xhttp = new XMLHttpRequest();
        var self = this;

        xhttp.onload = function() {
            callback.call(self, []);
        }

        xhttp.open("DELETE", this._backendUrl + "/items", false);
        xhttp.send();
    };

    // Export to window
    window.app = window.app || {};
    window.app.Store = Store;
})(window);
