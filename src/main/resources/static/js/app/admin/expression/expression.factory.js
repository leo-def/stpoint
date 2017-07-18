(function(){
    "use strict";
    angular.module('app.admin.expression')
            .factory('adminExpressionFactory',adminExpressionFactory);
    
    function adminExpressionFactory(appFactory, dataFactory){
        var service = {
            remove: remove,
            //Dialog
            /*
            create: create,
            edit: edit,
            */
            Data:{
            	list: dataList,
            	save: dataSave,
            	remove: dataRemove
            }
        };
        return service;
        
        function remove(ev, host, row, success, error){
        	
        	 return appFactory.dialog.simpleDialog(
        			 ev,
        			 'Deseja excluir esta expressão?',
        			 'Esta ação não poderá ser revertida',
        			 'Deseja excluir esta expressão?',
        			 'Sim', 'Não',
        			 function(){
        				 service.Data.remove(host, row, success, error);
        			 },
        			 function(){});
        };
        //Dialog
        /*
        function create(ev, host, success, error){
        	
        	return appFactory.dialog.customDialog(
        			ev,
        			{row: {}},
        			appFactory.dialog.controller,
        			'app/admin/expression/form.tpl.html', 
        			function(answer){
        				service.Data.save(host, answer, success, error)
        			} ,
        			function(){});
        };

        function edit(ev, host, row, success, error){
        	
        	return appFactory.dialog.customDialog(
        			ev,
        			{row: row},
        			appFactory.dialog.controller,
        			'app/admin/expression/form.tpl.html', 
        			function(answer){
        				service.Data.save(host, answer, success, error)
        			} ,
        			function(){});
        };
        */
        //data
        function dataList(host, success, error){
            return dataFactory.get(host)
                    .then(success, error);
        }
        
        function dataSave(host, expression, success, error){
            return dataFactory.post(host, expression)
                    .then(success, error);
        }
        
        function dataRemove(host, expression, success, error){
            return dataFactory.del(host+expression.id)
                    .then(success, error);
        }
    }
})();

