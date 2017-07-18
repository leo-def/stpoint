(function(){
    "use strict";
    angular.module('app.confirmAccount')
            .factory('confirmAccountFactory',confirmAccountFactory);
    
    function confirmAccountFactory(dataFactory){
        var service = {
        		Data: {
        			confirmAccount: dataConfirmAccount
	        	}
        };
        return service;
       
        function dataConfirmAccount(dto, success, error){
            return dataFactory.post('public/confirmAccount/', dto)
                .then(success, error); 
        }
    }
})();