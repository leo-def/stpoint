(function(){
    "use strict";
    angular.module('app.situation')
            .factory('situationFactory',situationFactory);
    
    function situationFactory(authTokenFactory, dataFactory){
        var service = {
            Data: {
            	parentsByAccountType: dataParentsByAccountType,
            	byParentsAndByAccountType: dataByParentsAndByAccountType,
            	get: dataGet,
            }
            
        };
        return service;
        
        function dataParentsByAccountType(success, error){
        	return dataFactory.get('authenticated/situation/parents/byAccountType/'+ authTokenFactory.user().accountType+'/')
           		.then(success, error);
        }
        
	    function dataGet(id, success, error){
	    	dataFactory.get('authenticated/situation/'+id+'/')
	        	.then(success, error);
	    }
	    
	    function dataByParentsAndByAccountType(parent_id, success, error){
	    	return dataFactory.get('authenticated/situation/'+parent_id+'/byAccountType/'+ authTokenFactory.user().accountType+'/')
       			.then(success, error);
	    }
    }

})();

