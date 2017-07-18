(function(){
    "use strict";
    angular.module('app.page')
            .controller('pageController',pageController);
    
    function pageController($scope, $location, appFactory){
        var vm = this;
        vm.toHome = toHome;
        vm.toRegister = toRegister;
        vm.index = index;
        
        vm.index();
        
        function index(){
            appFactory.sidenav.setExists(false);
        }
        
        function toHome(){
            $location.path('/page/home/');
        }
        
        function toRegister(){
            $location.path('/user/register/');
        }
    }
})();


