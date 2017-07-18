(function(){
    angular.module('app.admin.value')
            .directive("valueManage", directive);
    function directive() {
        var directive = {
            restrict: 'EA',
            templateUrl: 'app/admin/value/directive/value-manage.tpl.html',
            scope: {
                host: '@',
            },
            controller : dController,
            controllerAs: 'vm'
            };
            return directive;
         };
    function dController($scope, $mdDialog, appFactory, constantsFactory, adminValueFactory){
        var vm = this;
        vm.host = $scope.host;
        vm.values = [];
             
        vm.index = index;
        vm.all = all;
        vm.toggle = toggle;
        vm.isExtended = isExtended;
        vm.create = create;
        vm.save = save;
        vm.remove = remove;
        vm.upload = upload;

        vm.alert = appFactory.alert;
        vm.languages = constantsFactory.languages;
        
        vm.index();
        
        $scope.$watch(
            function(){
                return $scope.host;
            },function(newValue){
                vm.host = newValue;
                all();
            });

        function index(){
        	all();
        };
             
        function all(){
                 adminValueFactory.Data.list(vm.host,
                    function(response){
                        vm.values = response.data;
                    }, function(){
                        vm.alert('Erro ao carregar valores');
                    });
             };
             
        function toggle(ev, pindex, row){
                var original = false;
            	if(!row.$$extend){
                    row.$$bkg = angular.copy(row);
               	}else{
                    row = angular.copy(row.$$bkg);
                    original = true;
                }
            	for(var index in vm.values){
                    vm.values[index].$$extend = false;
                    if(!vm.values[index].id){
                        if(vm.values[index].$$new){
                            vm.values[index].$$new = false;
                        }else{
                            vm.values.splice(index, 1);
                        }
                        
                    }
                }
               	row.$$extend = !original;
                vm.values[pindex] = row;
             };
           
        function isExtended(ev, index, row){
               if(!row.$$extend){row.$$extend = false;}
               return row.$$extend; 
           };

        function create(ev){
                vm.values.unshift({$$extend:false, $$new: true});
                toggle(ev, 0,vm.values[0]);
            };

        function save(ev, row){
                vm.loading = true;
                adminValueFactory.Data.save(vm.host, row,
                    function(response){
                        vm.loading = false;
                        vm.values = response.data;
                        vm.alert('Valor salvo com sucesso');
                    }, function(error){
                        vm.loading = false;
                        vm.alert('Erro ao salvar valor');
                    });
            };

        function remove(ev, row){
                vm.loading = true;
                adminValueFactory.remove(ev, vm.host, row,
                    function(response){
                        vm.loading = false;
                        vm.values = response.data;
                        vm.alert('Valor removido com sucesso');
                    }, function(error){
                        vm.loading = false;
                        vm.alert('Erro ao remover valor');
                    });
            };
            
            //upload
            function upload($event, row){
            	 if(!vm.host || !row  || !row.$$upload){return;}
            	adminValueFactory.Data.upload(
                        vm.host,
                        row,
                        row.$$upload,
                        function(response){
                            vm.loading = false;
                        	vm.values = response.data;
                            vm.alert('Upload realizado com sucesso');
                        }, function(error){
                            vm.loading = false;
                            vm.alert('Erro ao realizar Upload');
                        });
            }
  	}
})();


