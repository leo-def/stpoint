(function(){
    "use strict";
    angular.module('app.admin.situation')
            .factory('adminSituationFactory',situationFactory);
    
    function situationFactory(appFactory, dataFactory){
        var service = {
        	remove: remove,
        	create: create,
        	edit: edit,
        	
            Data:{
                all: dataAll,
                save: dataSave,
                remove: dataRemove,
                upload: dataUpload,
                allAsItem: dataAllAsItem,
            }
        };
        return service;
        
        function remove(ev, row, success, error){
       	 	return appFactory.dialog.simpleDialog(
       			 ev,
       			 'Deseja excluir esta situação?',
       			 'Esta ação não poderá ser revertida',
       			 'Deseja excluir esta situação?',
       			 'Sim', 'Não',
       			 function(){
       				 service.Data.remove(row, success, error);
       			 },
       			 function(){});
       };
       
       function create(ev, success, error){
       	
       		return appFactory.dialog.customDialog(
       			ev,
       			{row: {}},
       			DialogController,
       			'app/admin/situation/form-dialog.tpl.html', 
       			function(answer){
       				service.Data.save(answer, success, error);
       			} ,
       			function(){});
       };

       function edit(ev, row, success, error){
       	
       		return appFactory.dialog.customDialog(
       			ev,
       			{row: row},
       			DialogController,
       			'app/admin/situation/form-dialog.tpl.html', 
       			function(answer){
       				service.Data.save(answer, success, error);
       			} ,
       			function(){});
       };
        
        function dataAll( success, error){
            return dataFactory.get('admin/situation/')
                    .then(success, error);
        }
        
        function dataSave(situation, success, error){
            return dataFactory.post('admin/situation/', situation)
                    .then(success, error);
        }
        
        function dataRemove(situation, success, error){
            return dataFactory.del('admin/situation/'+situation.id)
                    .then(success, error);
        }
       
        function dataUpload(situation, file, success, error){
            return dataFactory.upload('admin/situation/'+situation.id+'/upload/', file)
                .then(success, error);
        }
        
        function dataAllAsItem(success, error){
        	return dataFactory.get('admin/situation/asItem/')
            	.then(success, error);
        }
        function DialogController($scope, $mdDialog, constantsFactory, row) {
            var vm = this;
            vm.hide = hide;
            vm.cancel = cancel;
            vm.answer = answer;
            vm.accountTypes = constantsFactory.accountTypes;
            vm.row = row;
           
            
            function hide() {
                $mdDialog.hide();
            };

            function cancel() {
                $mdDialog.cancel();
            };

            function answer(pasnwer) {
                $mdDialog.hide(pasnwer);
            };
        };
    }
})();

