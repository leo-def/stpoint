(function(){
    "use strict";
    angular.module('app.admin.situation',[])
    	.config(routerConfig);
    
    function routerConfig($routeProvider) {
        var templateSituation = {
                        title: 'Situações',
                        templateUrl: 'app/admin/situation/index.tpl.html',
                        controller: 'adminSituationController',
                        controllerAs: 'vm'
        };
                
        $routeProvider
        	.when('/admin/dashboard',templateSituation)
                .when('/admin/situation',templateSituation);
    }
})();
