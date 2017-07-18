(function(){
    "use strict";
    angular.module('app.admin.user',[])
    	.config(routerConfig);
    
    function routerConfig($routeProvider) {
        var templateUser = {
                        title: 'Usuários',
                        templateUrl: 'app/admin/user/index.tpl.html',
                        controller: 'adminUserController',
                        controllerAs: 'vm'
        };
                
        $routeProvider
                .when('/admin/user',templateUser)
                .when('/admin/user/:id/changePassword',templateUser);
    }
})();
