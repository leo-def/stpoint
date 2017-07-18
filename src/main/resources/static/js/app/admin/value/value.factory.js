(function(){
    "use strict";
    angular.module('app.admin.value')
            .factory('adminValueFactory',adminValueFactory);
    
    function adminValueFactory(appFactory, dataFactory){
         var service = {
            remove: remove,
            Data: {
            	list: dataList,
            	save: dataSave,
            	remove: dataRemove,
            	upload: dataUpload	
            }
        };
        return service;

        
        function remove(ev, host, row, success, error){
       	 	return appFactory.dialog.simpleDialog(
       			 ev,
       			'Deseja excluir este valor?',
       			'Esta ação não poderá ser revertida',
       			'Deseja excluir este valor?',
       			 'Sim', 'Não',
       			 function(){
       				 service.Data.remove(host, row, success, error);
       			 },
       			 function(){});
       };
       
       function create(ev, host, situation, success, error){
       	
       		return appFactory.dialog.customDialog(
       			ev,
       			{row: {situation: situation}},
       			appFactory.dialog.controller,
       			'app/admin/value/form.tpl.html', 
       			function(answer){
       				service.Data.save(host, answer, success, error)
       			} ,
       			function(){});
       };

       function edit(ev, host,  row, success, error){
       	
       		return appFactory.dialog.customDialog(
       			ev,
       			{row: row},
       			appFactory.dialog.controller,
       			'app/admin/value/form.tpl.html', 
       			function(answer){
       				service.Data.save(host, answer, success, error)
       			} ,
       			function(){});
       };
        
        function dataList(host, success, error){
            return dataFactory.get(host)
                    .then(success, error);
        }
        
        function dataSave(host, value, success, error){
            return dataFactory.post(host, value)
                    .then(success, error);
        }
        
        function dataRemove(host, value, success, error){
            return dataFactory.del(host+value.id)
                    .then(success, error);
        }
        
        function dataUpload(host, value, file, success, error){
            return dataFactory.upload(host+value.id+'/upload', file)
                .then(success, error);
        }

      
    }
})();

