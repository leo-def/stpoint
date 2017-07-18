(function(){
    "use strict";
    angular.module('app.user',[])
     	.config(routerConfig);
    
    function routerConfig($routeProvider) {
         var templateProfile = {
                        title: 'Perfil',
                        templateUrl: 'app/user/profile.tpl.html',
                        controller: 'profileController',
                        controllerAs: 'vm'
            };
        var templateRegister = {
                        title: 'Register',
                        templateUrl: 'app/user/register.tpl.html',
                        controller: 'registerController',
                        controllerAs: 'vm'
            };
            
        var templateChangePassword = {
                        title: 'Alterar Senha',
                        templateUrl: 'app/user/change-password.tpl.html',
                        controller: 'changePasswordController',
                        controllerAs: 'vm'
            };
        
        
        $routeProvider
            .when('/user/profile',templateProfile)
            .when('/user/register',templateRegister)
            .when('/user/changePassword',templateChangePassword);
    
    }
})();
