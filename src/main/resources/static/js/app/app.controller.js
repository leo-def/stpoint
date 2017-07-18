(function(){
    "use strict";
    angular.module('app')
            .controller('appController',appController);
    
    function appController($scope, $location,  appFactory, authTokenFactory, authFactory, dataFactory){
        var vm = this;
        
        vm.index = index;
        
        //to 
        vm.toHome = toHome;
        vm.toLogin = toLogin;
        vm.logout = logout;
        vm.toProfile = toProfile;
        vm.toChangePassword = toChangePassword;
        vm.toAdminUser = toAdminUser;
        vm.toAdminSituation = toAdminSituation;
        vm.toUserSituation = toUserSituation;
        //sidenav
        vm.sidenav = appFactory.sidenav;

        //appFactory
        vm.fabToolbar = appFactory.fabToolbar;
        
        //authFactory
        vm.isAuth = authTokenFactory.isAuth;
        vm.user = authTokenFactory.user;
        vm.token = authTokenFactory.token;
        vm.isAdmin = authFactory.isAdmin;
        vm.isUser = authFactory.isUser;
        vm.loading = false;
        vm.index();
  
        function index(){
            appFactory.sidenav.setExists(false);
        }
       // server - conection test
        function test(){
            dataFactory.post('test/', {App:'STPoint', flag:'Test'}).then(function(response){  
            });
        };

        // to
        function logout(){
            vm.loading = true;
            authFactory.logout( function(){
                authFactory.logoutDispatcher();
                vm.loading = false;
            }, function(){
                vm.loading = false;
            });
         };
        
         function toHome(){
             $location.path('/');
         };
         
        function toLogin(){
            $location.path('auth/login/');
        };
        
        function toProfile(){
            $location.path('user/profile/');
        };
        
        function toChangePassword(){
            $location.path('user/changePassword/');
        };
        
        function toAdminUser(){
            $location.path('admin/user/');
        }
        
        function toAdminSituation(){
            $location.path('admin/situation/');
        }
        function toUserSituation(){
            $location.path('situation/');
        };
    }
})();