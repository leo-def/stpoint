(function(){
    "use strict";
    angular.module('app.confirmAccount',[])
            .config(routeConfig);
    
    function routeConfig($routeProvider) {

        var sendLink = {
            title: 'Confirmar Conta',
            templateUrl: 'app/confirm-account/confirm-account.tpl.html',
            controller: 'confirmAccountController',
            controllerAs: 'vm'
        };
        $routeProvider
                .when('/confirmAccount/:token', sendLink);
    }
})();


