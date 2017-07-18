(function(){
    "use strict";
    angular.module('app')
            .factory('gridListAdapter',gridListAdapter);
    
    function gridListAdapter(){
        var service = {
        		loadList: loadList,
        		colors: ['gray', 'green', 'yellow' ,'blue',
                 'darkBlue', 'deepBlue', 'purple',
                 'lightPurple', 'red', 'pink']  
        };
        return service;
        
        function loadList(list){
        	var count = 0;
        	for(var index in list){
        	    if(count >= service.colors.lenght){count = 0;}	
        	    list[index].$$color = service.colors[count];
        	    count++;
        	}
        	return list;
        };
        
    }
})();
