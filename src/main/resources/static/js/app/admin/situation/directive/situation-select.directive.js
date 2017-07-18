(function(){
    angular.module('app')
            .directive("situationSelect", directive);
    // <situation-select model="valor-selecionado" label="texto mostrado ao usuário"></situation-select>
    function directive() {
        var directive = {
            restrict: 'EA',
            templateUrl: 'app/admin/situation/directive/situation-select.tpl.html',
            scope: {
                model: '=', // é o objeto aonde deve ser setado 
                label: '@',
            },
            controller : dController,
            controllerAs: 'vm'
            };
            return directive;
         };
         function dController($scope, appFactory, adminSituationFactory){
             var vm = this;
             
             //vars
             	vm.model = $scope.model;
             	vm.label = $scope.label;
             	
             	vm.situations = [];
             	vm.loading = false;
             	
             //locals
             	vm.index = index;
             
            //factory
             	vm.alert = appFactory.alert;
             
             vm.index();
             
             $scope.$watch(function(){
                 return vm.model;
             }, function(newValue){
                 $scope.model = newValue;
             });
             
             $scope.$watch(function(){
                return $scope.model;
             }, function(newValue){
                vm.model = newValue;
             });
             
             function index(){
            	 all();
                 if(!vm.label){
                     vm.label = '';
                 }
             };
             
             function all(){
            	 vm.loading = true;
            	 adminSituationFactory.Data.allAsItem(
            			 function(response){
            				 vm.loading = false;
            				 vm.situations = response.data;
            				 
            			 }, function(error){
            				 vm.loading = false;
            				 vm.alert('Não foi possivel carregar as opções de situações');
            			 });
             }
         }
})();



