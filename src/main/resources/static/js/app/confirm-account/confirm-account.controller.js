(function(){
    "use strict";
    angular.module('app.confirmAccount')
            .controller('confirmAccountController',confirmAccountController);
    
    function confirmAccountController($scope, $location, $routeParams, appFactory, confirmAccountFactory){
        var vm = this;
        //vars
        	vm.loading = false;
        	vm.dto = {};
        	
    	//locals
        	vm.index = index;
        	vm.send = send;
        	vm.toLogin = toLogin;
        	
        //factory
        	 vm.alert = appFactory.alert;
        	 
        vm.index();
        
        function index(){
            appFactory.sidenav.setExists(false);
            vm.dto.token = $routeParams.token;
        }
        
        function send(){
            vm.loading = true;
            confirmAccountFactory.Data.confirmAccount(
                    vm.dto,
                    function(response){
                        vm.loading = false;
                        vm.alert('Conta ativa com sucesso');
                        vm.toLogin();
                    }, function(){
                        vm.loading = false;
                        vm.alert("Erro ao ativar conta");
                    });
        }
        
        function toLogin(ev){
            $location.path('/auth/login/');
        };
       
       
    }
})();


