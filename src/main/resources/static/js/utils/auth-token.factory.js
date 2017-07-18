(function(){
    "use strict";
    angular.module("utils")
            .factory("authTokenFactory", authTokenFactory);
    
    function authTokenFactory($rootScope, $cookieStore, $window ){
        
    	var service = {
            //set
            auth: auth,
            updateUser: updateUser,
            remove: remove,
            //is
            isAuth: isAuth,
            isUserEmpty: isUserEmpty,
            //get
            token: token,
            user: user,
            authority: authority,
            accountType: accountType,
            //values
            authTokenHeader: 'x-auth-token',
        };
        return service;
        
        //set
        function auth(user, token){
        	if(!user || !token){return;}
        	$window.localStorage.setItem("user", JSON.stringify(user));
        	$window.localStorage.setItem("token", token);	
        };  
        
        //set
        function updateUser(user){
            if(!user){return ;}
            $window.localStorage.setItem("user", JSON.stringify(user));
        };
        
        //set
        function remove(){
        	$window.localStorage.removeItem("token");
        	$window.localStorage.removeItem("user");
        };
        
        //is
        function isAuth(){
            return (token() !== null && token() !== undefined);
        };
        //is
        function isUserEmpty(){
            return (user() === null || user() === undefined);
        };
        //get
        function token(){
        	var token = $window.localStorage.getItem("token");
        	if(token  === "undefined"){return null;}
            return angular.copy(token);
        };
        //get
        function user(){
        	var user = $window.localStorage.getItem("user");
        	if(user  === "undefined"){return null;}
            return  JSON.parse(user);//angular.fromJson(local_user); JSON.stringify(
        };
        
        //get
        function authority(){
            if(!user()){return null;}
            return user().authority;
        };
        //get
        function accountType(){
            if(!user()){return null;}
            return user().accountType;
        };
       
        	//JSON.stringify(user));
	   		//angular.fromJson(getItem("user"));
    }
})();
