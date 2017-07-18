(function(){
    "use strict";
    angular.module('app.user')
            .controller('changePasswordController', changePasswordController);
    
    function changePasswordController($scope, userFactory, authFactory, authTokenFactory, appFactory, constantsFactory){
        var vm = this;
        
        //vars
            vm.loading = false;
            vm.user = {};
        //locals
            vm.index = index;
            vm.changePassword = changePassword;

        //factory
            vm.alert = appFactory.alert;

        
        vm.index();
        

        
        function index(){
            appFactory.sidenav.setExists(false);
            appFactory.back.setExecute(authFactory.loginDispatcher);
        }
        function changePassword(ev){
            vm.loading = true;
            userFactory.Data.changePassword(vm.user,
                function(response){
                    vm.loading = false;
                    vm.alert('Senha atualizada com sucesso');
                    authFactory.loginDispatcher();
                }, function(){
                    vm.loading = false;
                    vm.alert('Erro ao atualizar senha');
                });
        }
    }
})();






        
