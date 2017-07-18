(function(){
    angular.module('app')
            .directive("inputSelect", directive);
    function directive() {
        var directive = {
            restrict: 'EA',
            templateUrl: 'app/directive/input-select.tpl.html',
            scope: {
                model: '=', // é o objeto aonde deve ser setado 
                options: '=',
                label: '@',
                init: '=?'
            },
            controller : dController,
            controllerAs: 'vm'
            };
            return directive;
         };
         function dController($scope){
             var vm = this;
             vm.model = $scope.model;
             vm.options = $scope.options;
             vm.label = $scope.label;
             vm.index = index;
             
             vm.index();
             
             $scope.$watch(function(){
                 return vm.model;
             }, function(newValue){
                 $scope.model = vm.model;
             });
             
             $scope.$watch(function(){
                return $scope.model;
             }, function(newValue){
                vm.model = $scope.model;
             });
             
             function index(){
                 
                 if(!vm.options){
                     vm.options = [];
                 }
                 
                 if(!vm.label){
                     vm.label = '';
                 }
                 
                 if(vm.options.length > 0 && $scope.init && ! vm.model){
                     vm.model = vm.options[0].value;
                 }
                 
             };
         }
})();



