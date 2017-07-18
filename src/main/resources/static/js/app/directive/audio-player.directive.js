(function(){
    "use strict";
     angular.module('app')
         .directive('audioPlayer', directive);
 
       function directive() {
        var directive = {
            restrict: 'EA',
            templateUrl: 'app/directive/audio-player.tpl.html',
            scope: {
                url: '=',
                label: '=?',
                color: '=?',
            },
            controller : dController,
            controllerAs: 'vm'
            };
            return directive;
         };
         
         function dController($scope, $element, $httpParamSerializer, $sce){
             var vm = this;
             vm.index = index;
             vm.initAudio = initAudio;
             vm.togglePlay = togglePlay;
             
             vm.url = $scope.url;
             vm.label = $scope.label;
             vm.isPlaying = false;
             vm.myAudio =null;
             vm.color = 'black';
             
             vm.index();
             
             $scope.$watch(function(){
                 return $scope.url;
             }, function(newValue){
                 vm.url = $scope.url;
                 if(vm.myAudio){
                 	vm.myAudio.pause();
                 }
                 vm.initAudio();
             });
             
            function index(){
            	if($scope.color){
            		vm.color = $scope.color;
            	}
                vm.url = $scope.url;
                vm.label = $scope.label;
                if(vm.myAudio){
                	vm.myAudio.pause();
                }
                vm.initAudio();
            };
           
           function initAudio(){
               vm.myAudio = new Audio(vm.url);
           }
            function togglePlay() {
                if (vm.isPlaying) {
                  vm.myAudio.pause();
                  vm.isPlaying = false;
                } else {
                  vm.myAudio.play();
                  vm.isPlaying = true;
                }
            };
            vm.myAudio.onplaying = function() {
              vm.isPlaying = true;
            };
            vm.myAudio.onpause = function() {
              vm.isPlaying = false;
            }
    }
})();
