(function(){
    "use strict";
    angular.module('app.auth')
            .controller('authController',authController);
    
    function authController($location, authFactory, appFactory){
        var vm = this;
        vm.index = index;
        vm.login = login;
        vm.logout = logout;
        vm.toRegister = toRegister;
        vm.toResetPassword = toResetPassword;
        //factory
        vm.alert = appFactory.alert;
    
       
       
        vm.loading = false;
        
        vm.index();

        function index(){
            appFactory.sidenav.setExists(false);
        }
        
        function login(){
           vm.loading = true;
           authFactory.Data.login(vm.user,
        	function(response){
               vm.loading = false;
               authFactory.loginDispatcher();
               vm.alert("Login realizado com sucesso");
           }, function(){
               vm.loading = false;
               vm.alert("erro ao realizar login");
           });
        };
        
        function logout(){
           vm.loading = true;
           authFactory.logout( function(){
               vm.loading = false;
        	   authFactory.logoutDispatcher();
        	   vm.alert("Até mais");
           }, function(){
               vm.loading = false;
           });
        };
        
        function toRegister(){
            $location.path('/user/register/');
        };
        
        function toResetPassword(){
            $location.path('/resetPassword/');
        }
        
    }
})();


