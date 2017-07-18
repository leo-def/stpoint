(function(){
    angular.module('app')
            .directive("listExpressions", directive);
    function directive() {
        var directive = {
            restrict: 'EA',
            templateUrl: 'app/expression/directive/list-expressions-directive.tpl.html',
            scope: {
                situation: '=',
            },
            controller : dController,
            controllerAs: 'vm'
            };
            return directive;
         };
         
         function dController($scope, appFactory, expressionFactory){
             var vm = this;
             vm.title = 'Expressões';
             vm.situation = $scope.situation;
             vm.expressions = [];
             vm.filter = '';
             vm.loading = false;
             
             vm.index = index;
             vm.all = all;

             //appFactory
             vm.alert = appFactory.alert;
             
             
             //run
             vm.index();
             
             //watch
             $scope.$watch(function(){
                 return $scope.situation;
             }, function(newValue){
                 vm.situation = newValue;
                 vm.all();
             });
             
             function index(){
                 all();
             };
             
             function all(){
            	 vm.loading = true;
                 expressionFactory.Data.bySituation(vm.situation,
                    function(response){
                	 	vm.loading = false;
                	 	console.log(response.data);
                	 	vm.expressions = response.data;
                    }, function(){
                    	vm.loading = false;
                    	vm.alert('Erro ao carregar expressões de: '+vm.situation.title);
                    });
             };
             
         }
})();


