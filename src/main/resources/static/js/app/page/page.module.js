(function(){
    "use strict";
    angular.module('app.page',[])
        .config(routerConfig);
    function routerConfig($routeProvider) {
        var templateHome = {
                        title: 'Páginas',
                        templateUrl: 'app/page/home.tpl.html',
                        controller: 'pageController',
                        controllerAs: 'vm'
        };
        $routeProvider
            .when('/home',templateHome);
    }
})();
