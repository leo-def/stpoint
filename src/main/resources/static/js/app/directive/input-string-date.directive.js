(function(){
    angular.module('app')
            .directive("inputStringDate", directive);

    function directive() {
        var directive = {
            restrict: 'EA',
            templateUrl: 'app/directive/input-string-date.tpl.html',
            //template: '<h1>Teste</h1>',
            scope: {
                model: '=',
                format: '=?',
                date: '=?'
            },
            
            controller : inputStringDateController,
            controllerAs: 'vm'
            };
            return directive;
         };
         function inputStringDateController($scope){
             var vm = this;
             
             //vars
             	vm.date = new Date();
             
             //locals
             	vm.index = index;
            
             
             vm.index();
             
             $scope.$watch(function(){
                 return $scope.model;
             }, function(newValue){
                 var date = new Date();
                 if(newValue){
                     date = moment(newValue, getFormat()).toDate();
                 }
                 vm.date = date;
                 $scope.date = date;
             });
             
             $scope.$watch(function(){
                 return vm.date;
             }, function(newValue){
                 if(newValue){
                    $scope.model = moment(newValue).format(getFormat());
                    $scope.date = newValue;
                }
             });
             
             function getFormat(){
                 if(!$scope.format){
                     return "DD/MM/YYYY";
                 }
                 return $scope.format;
             } 
             
             function index(){
                 var date = new Date();
                 if($scope.model){
                     date = moment($scope.model, getFormat()).toDate();
                 }
                 vm.date = date;
                 $scope.date = date;
                 
             }
         }
})();


