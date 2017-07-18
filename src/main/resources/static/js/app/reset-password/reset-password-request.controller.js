(function() {
    "use strict";
    angular.module('app.resetPassword')
            .controller('resetPasswordRequestController', resetPasswordRequestController);

    function resetPasswordRequestController($scope, $location, appFactory, resetPasswordFactory) {
        var vm = this;
        
        //vars
            vm.dto = {};
            vm.loading = false;

        //locals
            vm.index = index;
            vm.send = send;
            vm.toLogin = toLogin;

         //factory
            vm.alert = appFactory.alert;


        vm.index();

        function index() {
            appFactory.sidenav.setExists(false);
          
        }

        function send(ev) {
            vm.loading = false;
            resetPasswordFactory.Data.resetPasswordRequest(
                    vm.dto,
                    function(response) {
                        vm.loading = false;
                        vm.alert('Você receberá um e-mail com um link para alterar a senha');
                        vm.toLogin();
                    }, function() {
                        vm.loading = false;
                        vm.alert('Não foi possivel enviar link para alterar senha');
                    });
        }
        
        
        function toLogin(ev){
            $location.path('/auth/login/');
        };

    }
})();


