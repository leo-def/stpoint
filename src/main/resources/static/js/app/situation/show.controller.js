(function(){
    "use strict";
    angular.module('app.situation')
            .controller('showController',showController);
    
    function showController($scope, $location, $routeParams, situationFactory, appFactory){
    	var vm = this;
    	//vars
	        vm.loading = false;
	        vm.situation = null;
        
		//locals
	        vm.index = index;
	        vm.get = get;
	        vm.back = back;
	        vm.showParent = showParent;
	        
	   //factory
	        vm.alert = appFactory.alert;
	        
	        vm.index();
        
        function index(){
            appFactory.sidenav.setExists(false);
            appFactory.back.setExecute(vm.back)
            get($routeParams.id);
        };
        
        function get(id){
        	vm.loading = false;
        	situationFactory.Data.get(
        			id,
        			function(response){
        				vm.loading = true;
        				vm.situation = response.data;
        			}, function(){
        				vm.loading = false;
        				vm.alert('Não foi possivel carregar a situação');
        			});
        }
        
        function back(){
        	$location.path('dashboard/');
        };
       
        function showParent(){
        	if(!vm.situation.parent){return;}
        	$location.path('dashboard/'+vm.situation.parent.id);
        }
    }
})();


