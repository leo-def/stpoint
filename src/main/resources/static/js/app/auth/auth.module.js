(function(){
    "use strict";
    angular.module('app.auth', [])
        .config(routerConfig);
    function routerConfig($routeProvider) {
        var templateLogin = {
                        title: 'Login',
                        templateUrl: 'app/auth/login.tpl.html',
                        controller: 'authController',
                        controllerAs: 'vm'
        };
        var templateRegister = {
                        title: 'Register',
                        templateUrl: 'app/auth/register.tpl.html',
                        controller: 'authController',
                        controllerAs: 'vm'
        };
                
        $routeProvider
            .when('/auth',templateLogin)
            .when('/auth/login',templateLogin);
            
           
    }
})();
