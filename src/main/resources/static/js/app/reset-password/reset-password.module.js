(function(){
    "use strict";
    angular.module('app.resetPassword',[])
            .config(routeConfig);
    
    function routeConfig($routeProvider) {

        var resetPassword = {
            title: 'Gerar nova senha',
            templateUrl: 'app/reset-password/reset-password-request.tpl.html',
            controller: 'resetPasswordRequestController',
            controllerAs: 'vm'
        };
        var confirmResetPassword = {
            title: 'Confirmar nova senha',
            templateUrl: 'app/reset-password/reset-password.tpl.html',
            controller: 'resetPasswordController',
            controllerAs: 'vm'
        };
        $routeProvider
            .when('/resetPassword', resetPassword)
            .when('/resetPassword/:token', confirmResetPassword);
    }
})();


