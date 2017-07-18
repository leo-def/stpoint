(function(){
    "use strict";
    angular.module('app.user')
            .controller('registerController',registerController);
    
    function registerController($scope, $location, userFactory, authTokenFactory, appFactory, constantsFactory){
        var vm = this;
        vm.index = index;
        vm.register = register;
        vm.toLogin = toLogin;
        //factory
        vm.alert = appFactory.alert;
        vm.languages = constantsFactory.languages;
        vm.genders = constantsFactory.genders;
    
       
       
        vm.loading = false;
        
        vm.index();

        function index(){
            appFactory.sidenav.setExists(false);
        }
        
        function register(){
            vm.loading = true;
            userFactory.Data.register(vm.user, function(){
            	vm.loading = false;
                vm.alert("Usuário salvo com sucesso");
                toLogin();
               
            }, function(){
                vm.loading = false;
                vm.alert("Erro ao salvar usuário");
            });
        };
        
        
        function toLogin(){
            $location.path('/auth/login/');
        };
        
    }
})();



