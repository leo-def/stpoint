(function(){
    angular.module('app.situation')
            .directive("situationList", directive);
    function directive() {
        var directive = {
            restrict: 'EA',
            templateUrl: 'app/situation/directive/situation-list-directive.tpl.html',
            scope: {
                parentid: '=?',
            },
            controller : dController,
            controllerAs: 'vm'
            };
            return directive;
         };
         
         function dController($scope, $location, 
        		 appFactory, situationFactory, gridListAdapter){
             var vm = this;
             //vars
	             vm.situations = [];
	             vm.filter = '';
	             vm.loading = false;
	             vm.color = 'white';
             
	         //locals
	             vm.index = index;
	             vm.loadSituations = loadSituations;
	             vm.parentsByAccountType = parentsByAccountType;
	             vm.byParentsAndByAccountType = byParentsAndByAccountType;
	             vm.show = show;
	             vm.isParent = isParent;
	             
             //appFactory
	             vm.alert = appFactory.alert;
             
             
             //run
             vm.index();
             
             //watch
             $scope.$watch(function(){
                 return $scope.parentid;
             }, function(newValue){
                 vm.parent_id = $scope.parentid;
                 vm.loadSituations();
             });
             
             function index(){
            	 vm.parent_id =  $scope.parentid;
            	 loadSituations();
             };
             
             function loadSituations(){
            	 if(vm.parent_id){
            		 vm.byParentsAndByAccountType(vm.parent_id);
            	 }else{
            		 vm.parentsByAccountType();
            	 }
             }
             
             function parentsByAccountType(){
            	 vm.loading = true;
             	situationFactory.Data.parentsByAccountType(function(response){
             		vm.situations = gridListAdapter.loadList(response.data);
             		console.log(vm.situations);
             		vm.loading = false;
             	}, function(){
             		vm.loading = false;
             	});
             };
             
             function byParentsAndByAccountType(){
            	 vm.loading = true;
             	situationFactory.Data.byParentsAndByAccountType(
	             	vm.parent_id,
	             	function(response){
	             		vm.situations = gridListAdapter.loadList(response.data);
	             		console.log(vm.situations);
	             		vm.loading = false;
	             	}, function(){
	             		vm.loading = false;
	             	});
             };
             
             function show(row){
             	$location.path('dashboard/'+row.id);
             };
             
             function isParent(row){
            	 return (row.id == vm.parent_id);
             }
         }
})();


