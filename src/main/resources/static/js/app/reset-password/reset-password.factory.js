(function(){
    "use strict";
    angular.module('app.resetPassword')
            .factory('resetPasswordFactory',resetPasswordFactory);
    
    function resetPasswordFactory(dataFactory){
        var service = {
            Data: {
                resetPasswordRequest: dataResetPasswordRequest,
                resetPassword: dataResetPassword
            }
        };
        return service;
        
        function dataResetPasswordRequest(dto, success, error){
            return dataFactory.post('public/resetPassword/request/',dto)
                .then(success, error);
        }
        function dataResetPassword(dto, success, error){
            return dataFactory.post('public/resetPassword/', dto)
                .then(success, error); 
        }
    }
})();