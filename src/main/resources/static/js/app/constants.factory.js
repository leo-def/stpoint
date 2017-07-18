(function(){
    "use strict";
    angular.module('app')
            .factory('constantsFactory',constantsFactory);
    
    function constantsFactory($mdToast, $mdDialog, $mdSidenav){
        var service = {
            
            accountTypes: [{value: 'PREMIUM', label:'Premium'},
                 {value: 'NORMAL', label:'Normal'},
                 {value: 'PUBLIC', label:'Público'}
             ],
             
            authorities:[{value: 'ADMIN', label:'Administrador'},
                 {value: 'USER', label:'Usuário'},
                 {value: 'GUEST', label:'Visitante'},
             ],
             
            genders: [
                 {value: 'MALE', label:'Masculino'},
                 {value: 'FEMALE', label:'Feminino'},
             ],
             
            languages: [{value: "EN", label:"English"},
                 {value: "EN-US", label:"English - United States"},
                 {value: "PT", label:"Português"},
                 {value: "PT-BR", label:"Português - Brasil"}],
        };
        return service;
    }
})();


