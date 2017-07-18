(function(){
    "use strict";
    angular.module('app.situation')
            .controller('situationController',situationController);
    
    function situationController($location, appFactory, authTokenFactory, situationFactory){
        var vm = this;
        	//vars
            	vm.loading = false;
            //locals
            	vm.index = index;
            
            //appFactory
            	vm.alert = appFactory.alert;
            	vm.url = appFactory.url;
            
            //authTokenFactory
            vm.user = authTokenFactory.user;     
            
            vm.index();
        
        
        function index(){
            appFactory.sidenav.setExists(false);
            console.log('index');
        };
        
        
    }
})();


