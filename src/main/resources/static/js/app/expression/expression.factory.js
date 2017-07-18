(function(){
    "use strict";
    angular.module('app.expression')
            .factory('expressionFactory',expressionFactory);
    
    function expressionFactory(authFactory, dataFactory, authTokenFactory){
        var service = {
            Data: {
            	bySituation: dataBySituation,
            	get: dataGet
            }
        };
        return service;
        
        function dataBySituation(situation, success, error){
            return dataFactory.get('authenticated/situation/'+situation.id+'/expressions',
            		{},
            		{'STP-Language': authTokenFactory.user().language,
                		'STP-Desired-Language': authTokenFactory.user().desiredLanguage})
                    .then(success, error);
        };
        
        function dataGet(id, sucess, error){
        	return dataFactory.get('authenticated/expression/'+id)
            .then(success, error); 
        };
    };
})();
/*
        function dataBySituation(situation, success, error){
            return dataFactory.get('authenticated/situation/'+situation.id+'/expressions',
            		{}
            		,{'STP-Language': authTokenFactory.user().language,
            		'STP-Desired-Language': authTokenFactory.user().desiredLanguage}
            		)
                    .then(success, error);
        }; 
 */
