(function() {
    angular.module('app.admin.expression')
            .directive("expressionManage", directive);
    function directive() {
        var directive = {
            restrict: 'EA',
            templateUrl: 'app/admin/expression/directive/expression-manage.tpl.html',
            scope: {
                host: '@',
            },
            controller: dController,
            controllerAs: 'vm'
        };
        return directive;
    };
    

    function dController($scope, $mdDialog, appFactory, adminExpressionFactory) {
        var vm = this;
        vm.expressions = [];
        vm.host = $scope.host,
                
        vm.filter = '';
        vm.valuesHost = 
        vm.loading = false;
        vm.expression = {};
        
        vm.index = index;
        vm.all = all;
        vm.save = save;
        vm.remove = remove;
        vm.cancel = cancel;
        vm.toggle = toggle;
        vm.isExtended = isExtended;
        vm.toggleForm = toggleForm;
        vm.isForm = isForm;
        vm.getValuesHost = getValuesHost;
        
        //Dialogs
        /*
         vm.create = create;
         vm.remove = remove;
         vm.edit = edit;
         */
        //appFactory
        vm.alert = appFactory.alert;

        vm.index();

        $scope.$watch(
            function(){
                return $scope.host;
            },function(newValue){
                vm.host = newValue;
                all();
            });

        function index() {
            all();
        };

        function all() {
            vm.loading = true;
            adminExpressionFactory.Data.list(vm.host,
                    function(response) {
                        vm.loading = false;
                        vm.cancel(null);
                        vm.expressions = response.data;
                    }, function() {
                vm.loading = false;
                vm.alert('Erro ao carregar expressões de: ' + vm.situation.title);
            });
        };

        function save(ev, row) {
            vm.loading = true;
            adminExpressionFactory.Data.save(vm.host, row,
                function(response) {
                    vm.loading = false;
                    vm.cancel(null);
                    vm.expressions = response.data;
                    vm.alert('Expressão salva com sucesso');
                }, function(error) {
                    vm.loading = false;
                    vm.alert('Erro ao salvar expressão');
            });
        }
        ;

        function remove(ev, row) {
            vm.loading = true;
            adminExpressionFactory.remove(
                    ev,
                    vm.host,
                    row,
                    function(response) {
                        vm.loading = false;
                        vm.cancel(null);
                        vm.expressions = response.data;
                        vm.alert('Expressões removida com sucesso ');
                    }, function() {
                vm.loading = false;
                vm.alert('Erro ao remover expressão ');
            });
        };
        function cancel(ev){
        	vm.expression = {};
        }
        
        function toggle(ev, index, row){
            if(!row.$$extend){ row.$$extend = false; }
            row.$$extend = ! row.$$extend;
        };

        function isExtended(ev, index, row){
            if(!row.$$extend){row.$$extend = false;}
        	return row.$$extend; 
        };
        
        function toggleForm(ev, index, row ){
                var original = false; 
        	if(!row.$$form){
                    row.$$bkg = angular.copy(row);
                }else{
                    original = true;
                    row = angular.copy(row.$$bkg);
                }
                row.$$form = !original;
                vm.expressions[index] = row;
        };
        
        function isForm(ev, index,  row){
        	if(!row.$$form){row.$$form = false;}
        	return row.$$form;
        };
        
        function getValuesHost(row){
            return 'admin/expression/' + row.id + '/values/';
        }


    }
    ;
})();


