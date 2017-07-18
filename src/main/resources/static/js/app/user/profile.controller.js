(function(){
    "use strict";
    angular.module('app.user')
            .controller('profileController',profileController);
    
    function profileController($scope, userFactory, authFactory, authTokenFactory, appFactory, constantsFactory){
        var vm = this;
        //vars
            vm.myFile = null;
            vm.loading = false;
            
        //locals
            vm.index = index;
            vm.profile = profile;
            vm.uploadFile = uploadFile;
            vm.updateUser = updateUser;
            
        //factory
            vm.alert = appFactory.alert;
            vm.upload = appFactory.upload;
            vm.languages = constantsFactory.languages;
            vm.genders = constantsFactory.genders;
            
            vm.user = {};
        
        vm.index();
        

        
        function index(){
            appFactory.sidenav.setExists(false);
            appFactory.back.setExecute(authFactory.loginDispatcher);
            vm.user = authTokenFactory.user();
            
        }
        function profile(ev){
            vm.loading = true;
            userFactory.Data.profile(vm.user,
                function(response){
                    vm.loading = false;
                    vm.updateUser(response.data);
                    vm.alert('Perfil atualizado com sucesso');
                    authFactory.loginDispatcher();
                }, function(){
                    vm.loading = false;
                    vm.alert('Erro ao atualizar perfil');
                });
        }
        
        function uploadFile(ev, user){
             vm.loading = true;
             userFactory.Data.profileImage(user.$$upload,
                    function(response){
                        vm.loading = false;
                        vm.updateUser(response.data);
                        vm.alert('Imagem de perfil atualizada com sucesso');
                    }, function(){
                        vm.loading = false;
                        vm.alert('Erro ao atualizar imagem de perfil');
                    });
            
        }
        
        function updateUser(user){
            authTokenFactory.updateUser(user);
            vm.user = authTokenFactory.user();
        }
    }
})();






        