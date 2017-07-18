(function(){
    "use strict";
    angular.module('app.user')
            .factory('userFactory',userFactory);
    
    function userFactory(dataFactory, authTokenFactory){
        var service = {
            
                Data:{
                    profile: dataProfile,
                    profileImage: dataProfileImage,
                    register: dataRegister,
                    changePassword: dataChangePassword,
                }     
        };
        return service;
        
        
        //data
        function dataProfile(user, success, error){
            console.log(user);
            return dataFactory.post('authenticated/profile/', user).then(success, error);
        };
        
        function dataProfileImage(file, success, error){
            return dataFactory.upload('authenticated/profile/upload/', file).then(success, error);
        };
        
        function dataRegister(user, success, error){
            return dataFactory.post('public/user/register/', user)
                    .then(success, error);
        };
        
        function dataChangePassword(user, success, error){
            return dataFactory.post('authenticated/profile/changePassword/', user)
                    .then(success, error);
        }
    }
})();

