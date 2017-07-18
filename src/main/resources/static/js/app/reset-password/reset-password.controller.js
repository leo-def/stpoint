(function(){
    "use strict";
    angular.module('app.resetPassword')
            .controller('resetPasswordController', resetPasswordController);
    
    function resetPasswordController($scope, $location, $routeParams, appFactory, resetPasswordFactory){
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
            console.log($routeParams.token);
        }
        
        function send(){
            console.log(vm.dto);
            vm.loading = true;
            resetPasswordFactory.Data.resetPassword(
                    vm.dto,
                    function(response){
                        vm.loading = false;
                        vm.alert('Senha alterada com sucesso');
                        vm.toLogin();
                    }, function(){
                        vm.loading = false;
                        vm.alert("Não foi possivel alterar a senha");
                    });
        }
        
        function toLogin(ev){
            $location.path('/auth/login/');
        };
       
    }
})();


