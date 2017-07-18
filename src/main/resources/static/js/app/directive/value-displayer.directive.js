(function(){
    "use strict";
     angular.module('app')
         .directive('valueDisplayer', directive);
 
       function directive() {
        var directive = {
            restrict: 'EA',
            templateUrl: 'app/directive/value-displayer.tpl.html',
            scope: {
                values: '=',
                language: '=?',
                desiredLanguage: '=?',
                color: '=?',
            },
            controller : dController,
            controllerAs: 'vm'
            };
            return directive;
         };
         
         function dController($scope, $element, $httpParamSerializer, $sce, authTokenFactory){
             var vm = this;
             vm.index = index;
             vm.getValueByLanguage = getValueByLanguage;
             vm.color = $scope.color;
             vm.user = null
             
             //authTokenFactory
             	vm.getUser = authTokenFactory.user;
             
             vm.index();
             
             //watch
             $scope.$watch(function(){
            	 return $scope.language;
             }, function(newValue){
            	 index();
             });
             
             $scope.$watch(function(){
            	 return $scope.desiredLanguage;
             }, function(newValue){
            	 index();
             });
             
             $scope.$watch(function(){
            	 return $scope.values;
             }, function(newValue){
            	 index();
             });
             
            function index(){
            	vm.user = vm.getUser();
            	vm.values = $scope.values;
            	vm.color = $scope.color;
            	
            	if(!$scope.language){
            		vm.language = vm.user.language;
            	}else{
            		vm.language = $scope.language;
            	}
            	
            	if(!$scope.desiredLanguage){
            		vm.desiredLanguage = vm.user.desiredLanguage;
            	}else{
            		vm.desiredLanguage = $scope.desiredLanguage;
            	}
            	
            	vm.languageValue = getValueByLanguage(vm.language);
            	vm.desiredLanguageValue = getValueByLanguage(vm.desiredLanguage);
            	
            };
            
            function getValueByLanguage(language){
            	for(var index in vm.values ){
            		var value = vm.values[index];
            		if(value.language === language){
            			return value;
            		}
            	}
            	return null;
            }
           
   
    }
})();
