(function(){
    "use strict";
    angular.module("utils")
            .factory("dataFactory", dataFactory);
    
    function dataFactory($http){
        var service = {
            get: get,
            post: post,
            put: put,
            del: del,
            upload: upload,
            baseUrl: 'rest/'
        };
        return service;
       
        function get(path, params, headers){
            return $http({
                method: 'GET',
                params : params, 
                url: service.baseUrl + path, 
                headers: headers
              });
        }
            
        function post(path, object, headers){
            return $http({
                method: 'POST',
                data: object,
                url: service.baseUrl + path, 
                headers: headers
              });
        }
            
        function put(path, object, headers){
            return $http({
                method: 'PUT',
                data: object,
                url: service.baseUrl + path, 
                headers: headers
              });
        }
            
        function del(path, headers){
            return $http({
                method: 'DELETE',
                url: service.baseUrl + path, 
                headers: headers
              });
        }
        function upload(path, file, headers){
            
               var fd = new FormData();
               fd.append('file', file);
               
               if(!headers){
                   headers = {};
               }
               headers['Content-Type'] = undefined;
               //
               return $http({
                  url: service.baseUrl + path, 
                  data: fd,   
                  method: 'POST',
                  transformRequest: angular.identity,
                  headers: headers
                  
              });
               
               
        }
    }
})();


 /*
            Request config 
            .method – {string} – HTTP method (e.g. 'GET', 'POST', etc)
            url – {string|TrustedObject} – Absolute or relative URL of the resource that is being requested; or an object created by a call to $sce.trustAsResourceUrl(url).
            params – {Object.<string|Object>} – Map of strings or objects which will be serialized with the paramSerializer and appended as GET parameters.
            data – {string|Object} – Data to be sent as the request message data.
            headers – {Object} – Map of strings or functions which return strings representing HTTP headers to send to the server. If the return value of a function is null, the header will not be sent. Functions accept a config object as an argument.
            eventHandlers - {Object} - Event listeners to be bound to the XMLHttpRequest object. To bind events to the XMLHttpRequest upload object, use uploadEventHandlers. The handler will be called in the context of a $apply block.
            uploadEventHandlers - {Object} - Event listeners to be bound to the XMLHttpRequest upload object. To bind events to the XMLHttpRequest object, use eventHandlers. The handler will be called in the context of a $apply block.
            xsrfHeaderName – {string} – Name of HTTP header to populate with the XSRF token.
            xsrfCookieName – {string} – Name of cookie containing the XSRF token.
            transformRequest – {function(data, headersGetter)|Array.<function(data, headersGetter)>} – transform function or an array of such functions. The transform function takes the http request body and headers and returns its transformed (typically serialized) version. See Overriding the Default Transformations
            transformResponse – {function(data, headersGetter, status)|Array.<function(data, headersGetter, status)>} – transform function or an array of such functions. The transform function takes the http response body, headers and status and returns its transformed (typically deserialized) version. See Overriding the Default Transformations
            paramSerializer - {string|function(Object<string,string>):string} - A function used to prepare the string representation of request parameters (specified as an object). If specified as string, it is interpreted as function registered with the $injector, which means you can create your own serializer by registering it as a service. The default serializer is the $httpParamSerializer; alternatively, you can use the $httpParamSerializerJQLike
            cache – {boolean|Object} – A boolean value or object created with $cacheFactory to enable or disable caching of the HTTP response. See $http Caching for more information.
            timeout – {number|Promise} – timeout in milliseconds, or promise that should abort the request when resolved.
            withCredentials - {boolean} - whether to set the withCredentials flag on the XHR object. See requests with credentials for more information.
            responseType - 
          */
        /* 
            Response
            data – {string|Object} – The response body transformed with the transform functions.
            status – {number} – HTTP status code of the response.
            headers – {function([headerName])} – Header getter function.
            config – {Object} – The configuration object that was used to generate the request.
            statusText – {string} – HTTP status text of the response.
         */ 