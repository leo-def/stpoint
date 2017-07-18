(function(){
    "use strict";
    angular.module('app.admin.situation')
            .controller('adminSituationController',situationController);
    
    function situationController($scope, adminSituationFactory, appFactory, constantsFactory){
        var vm = this;
        //vars
            vm.myFile =null;
            vm.loading = false;
            vm.situation = null;
            vm.situations = null;
            vm.extended = true;
            vm.selected_index = 0;
        
        //locals
            vm.uploadFile = uploadFile;
            vm.getSituations = getSituations;
            vm.getSituation = getSituation;
            vm.all = all;
            vm.index = index;
            vm.select = select;
            vm.edit = edit;
            vm.remove = remove;
            vm.create = create;
            vm.getValuesHost = getValuesHost;
            vm.getExpressionsHost = getExpressionsHost;
            vm.isSelected = isSelected;
            vm.isParent = isParent;
            vm.findById = findById;
            vm.selectById = selectById;
        //factory
            vm.alert = appFactory.alert;
            vm.upload = appFactory.upload;
        
        vm.index();
         
 
        function uploadFile(ev, situation){
             vm.loading = true;
             adminSituationFactory.Data.upload(
                    situation,
                    situation.$$upload,
                    function(response){
                        vm.loading = false;
                    	vm.situations = response.data;
                        vm.select(vm.selected_index);
                        vm.alert('Upload realizado com sucesso');
                    }, function(error){
                        vm.loading = false;
                        vm.alert('Erro ao realizar Upload');
                    });
            
        }
        
        function getSituations(){
            return vm.situations;
        };
        
        function getSituation(){
            return vm.situation;
        };
        
        function index(){
            appFactory.sidenav.setExists(true);
            all();
        };
        
        function all(){
            vm.loading = true;
            adminSituationFactory.Data.all(
                function(response){
                    vm.loading = false;
                    vm.situations = response.data;
                    vm.select(0);
                }, function(error){
                    vm.loading = false;
                    vm.alert('Erro ao resgatar situações');
                });
        };
        
        
        function select(pindex){
            if(!vm.situations){return;}
            if(!pindex){pindex = 0;}
            vm.selected_index = pindex;
            vm.situation = vm.situations[vm.selected_index];
        }
        
        function create(ev){
       	 vm.loading = true;
       	adminSituationFactory.create(
       			 ev,
       			 function(response){
       				 vm.loading = false;
       				 vm.situations = response.data;
                                 vm.select(vm.situations.length-1);
       				 vm.alert('Situação criada com sucesso ');
       			 }, function(){
       				 vm.loading = false;
       				 vm.alert('Erro ao criar situação ');
       			 });
        };
        
        function remove(ev, row){
       	 vm.loading = true;
       	 adminSituationFactory.remove(
       			 ev,
       			 row,
       			 function(response){
       				vm.loading = false;
                                vm.situations = response.data;
                                vm.select(0);
       		 		vm.alert('Situação removida com sucesso ');
       	 		}, function(){
       	 			vm.loading = false;
       	 			vm.alert('Erro ao remover situação ');
       	 		});
        };
        
        function edit(ev, row){
       	 vm.loading = true;
       	 adminSituationFactory.edit(
       			 ev,
       			 row,
       			 function(response){
       				vm.loading = false;
                                vm.situations = response.data;
                                vm.select(vm.selected_index);
       		 		vm.alert('Situação editada com sucesso');
       	 		}, function(){
       	 			vm.loading = false;
       	 			vm.alert('Erro ao editar situação');
       	 		});
       };
        
       function getValuesHost(situation){
            return 'admin/situation/' + situation.id + '/values/';
       };
       
       function getExpressionsHost(situation){
           return 'admin/situation/' + situation.id + '/expressions/';
       };
       
       function isSelected(situation){
       		return (situation.id == vm.situation.id);
       };
       
       function isParent(row){
    	   if(!vm.situation.parent){return false;}
    	   return (vm.situation.parent.id == row.id);
       }
       
       function findById(id){
    	   for(var index in vm.situations){
    		   if(vm.situations[index].id == id){
    			   return vm.situations[index];
    		   }
    	   }
       };
       
       function selectById(id){
    	   for(var index in vm.situations){
    		   if(vm.situations[index].id == id){
    			   vm.select(index);
    			   return;
    		   }
    	   }
       };

    };
})();


