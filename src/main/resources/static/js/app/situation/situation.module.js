(function(){
    "use strict";
    angular.module('app.situation',[])
    	.config(routerConfig);
    
    
    function routerConfig($routeProvider) {
        var templateIndex = {
                           title: 'Situações',
                           templateUrl: 'app/situation/index.tpl.html',
                           controller: 'situationController',
                           controllerAs: 'vm'
           };
        
        var templateShow= {
                title: 'Mostrar Situação',
                templateUrl: 'app/situation/show.tpl.html',
                controller: 'showController',
                controllerAs: 'vm'
};

           $routeProvider
           		.when('/dashboard/:id', templateShow)
           		.when('/dashboard', templateIndex)
           		.when('/situation', templateIndex);
    };
})();
