(function(){
    "use strict";
    angular.module('app.admin.user')
            .factory('adminUserFactory',userFactory);
    
    function userFactory(appFactory, dataFactory){
        var service = {
        		show: show,
        		remove: remove,
        		create: create,
        		edit: edit,
        		changePassword: changePassword,
            Data: {
            	save: dataSave,
            	update: dataUpdate,
            	remove: dataRemove,
            	all: dataAll,
                changePassword: dataChangePassword
            }
        };
        return service;
        
        function show(ev, row){
           	
       		return appFactory.dialog.customDialog(
       			ev,
       			{row: row},
       			DialogController,
       			'app/admin/user/show-dialog.tpl.html', 
       			function(answer){} ,
       			function(){});
       };
        
        function remove(ev, row, success, error){
       	 	return appFactory.dialog.simpleDialog(
       			 ev,
       			'Deseja excluir este usuário?',
       			'Esta ação não poderá ser revertida',
       			'Deseja excluir este usuário?',
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
       			'app/admin/user/form-dialog.tpl.html', 
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
       			'app/admin/user/form-dialog.tpl.html', 
       			function(answer){
       				service.Data.update(answer, success, error);
       			} ,
       			function(){});
       };
       
       function changePassword(ev, row, success, error){
    	   return appFactory.dialog.customDialog(
    			ev,
       			{row: row},
       			DialogController,
       			'app/admin/user/change-password-dialog.tpl.html', 
       			function(answer){
       				service.Data.changePassword(answer, success, error);
       			} ,
       			function(){});
       }
       
       function dataSave(user, success, error){
            return dataFactory.post('admin/user/', user)
                    .then(success, error);
        }
        
        function dataUpdate(user, success, error){
            return dataFactory.put('admin/user/'+user.id+'/', user)
                    .then(success, error);
        }
        
        function dataRemove(user, success, error){
            return dataFactory.del('admin/user/'+user.id)
                    .then(success, error);
        }
        
        function dataAll(success, error){
            return dataFactory.get('admin/user/')
                    .then(success, error);
        }
        
        function dataChangePassword(user, success, error){
            return dataFactory.post('admin/user/'+user.id+'/changePassword/', user)
                    .then(success, error);
        }
        
        function DialogController($scope, $mdDialog, constantsFactory, row) {
            var vm = this;
            vm.hide = hide;
            vm.cancel = cancel;
            vm.answer = answer;
            vm.accountTypes = constantsFactory.accountTypes;
            vm.authorities = constantsFactory.authorities;
            vm.genders = constantsFactory.genders;
            vm.languages = constantsFactory.languages;
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

