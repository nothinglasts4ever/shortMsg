(function () {
    'use strict';

    angular.module('mailboxApp', [
        'mailbox'
    ]);

    angular.module('mailbox', []);
})();
(function () {
    'use strict';

    angular
        .module('mailbox')
        .controller('MsgController', MsgController);

    MsgController.$inject = ['msgService'];

    function MsgController(msgService) {
        var vm = this;

        vm.users = [];
        vm.inbox = [];
        vm.outbox = [];

        activate();

        function activate() {
            msgService.getUsers().then(function (data) {
                vm.users = data;
            });

            msgService.getInbox().then(function (data) {
                vm.inbox = data;
            });

            msgService.getOutbox().then(function (data) {
                vm.outbox = data;
            });
        }
    }
})();
(function () {
    'use strict';

    angular
        .module('mailbox')
        .factory('msgService', msgService);

    msgService.$inject = ['$http'];

    function msgService($http) {
        var RESOURCE_URL = 'http://localhost:8080/';

        return {
            getUsers: getUsers,
            getInbox: getInbox,
            getOutbox: getOutbox
        };

        function getUsers() {
            return getRequest(RESOURCE_URL, 'users?id=1');
        }

        function getInbox() {
            return getRequest(RESOURCE_URL, 'messages/inbox?id=1');
        }

        function getOutbox() {
            return getRequest(RESOURCE_URL, 'messages/outbox?id=1');
        }

        function getRequest(host, url) {
            return $http.get(host + url).then(function (response) {
                console.log('Response received: ' + JSON.stringify(response.data));
                return response.data;
            }, function (error) {
                console.log('Failed to retrieve data: ' + JSON.stringify(error));
            });
        }
    }
})();