 (function(){
    "use strict";
    angular.module('app.auth')
            .factory('authFactory',authFactory);
    
    function authFactory( $location, dataFactory, authTokenFactory){
        var service = {
            name: 'authFactory',
            logout: logout,
            isUser: isUser, 
            isAdmin: isAdmin,
            loginDispatcher: loginDispatcher,
            logoutDispatcher: logoutDispatcher,
            Data:{
                login: dataLogin,
            }

        };
        return service;
        
        function logout(success, error){
            authTokenFactory.remove();
            success();
        };
       
        function loginDispatcher(){
            if (service.isUser()){
                $location.path('/dashboard/');
            }else if(service.isAdmin()){
                $location.path('/admin/dashboard/');
            }else{
                $location.path('auth/login/');
            }
        };
        function logoutDispatcher(){
            if(! authTokenFactory.isAuth()){
                $location.path('/');
            }
        }
        function isAdmin(){
        	return authTokenFactory.authority() === 'ADMIN';
        };
        function isUser(){
        	return authTokenFactory.authority() === 'USER';
        };
        
        function dataLogin(user, success, error){
             //var auth = $base64.encode(user.username+":"+user.password);
            //var headers = {"Authorization": "Basic " + auth};
            var headers = {Authorization : "Basic " + btoa(user.username + ":" + user.password)};
            return dataFactory.get('public/auth/login/', null, headers)
                    .then(
                    function(response){
                    	authTokenFactory.auth(response.data, response.headers()[authTokenFactory.authTokenHeader]);
                    	success(response);
                    }, error);
        };
        
        
        
        
    }
})();

