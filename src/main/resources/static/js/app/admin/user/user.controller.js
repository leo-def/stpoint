(function(){
    "use strict";
    angular.module('app.admin.user')
            .controller('adminUserController',userController);
    
    function userController($location, $mdDialog, $mdToast, adminUserFactory, appFactory){
        var vm = this;
        vm.title = 'Usuários';
        vm.users = [];
        vm.filter = '';
        vm.loading = false;
        
        vm.all = all;
        vm.index = index;
        vm.create = create;
        vm.remove = remove;
        vm.edit = edit;
        vm.changePassword = changePassword;
        
        vm.show = adminUserFactory.show;
        vm.alert = appFactory.alert;
         
        vm.index();
        
        function index(){
            appFactory.sidenav.setExists(false);
            vm.all();
        };
        
        function all(){
        	vm.loading = true;
            adminUserFactory.Data.all(
                function(response){
                	vm.loading = false;
                    vm.users = response.data;
                }, function(error){
                	vm.loading = false;
                    vm.alert('Erro ao resgatar usuários');
                });
        };
        
        function create(ev){
       	 vm.loading = true;
       	 adminUserFactory.create(
       			 ev,
       			 function(response){
       				 vm.loading = false;
                                 vm.users = response.data;
       				 vm.alert('Usuário criado com sucesso ');
       			 }, function(){
       				 vm.loading = false;
       				 vm.alert('Erro ao criar usuário ');
       			 });
        };
        
        function remove(ev, row){
       	 	vm.loading = true;
       	 	adminUserFactory.remove(
       			 ev,
       			 row,
       			 function(response){
       				 vm.loading = false;
                                 vm.users = response.data;
       		 		vm.alert('Usuário removido com sucesso ');
       	 		}, function(){
       	 			vm.loading = false;
       	 			vm.alert('Erro ao remover usuário ');
       	 		});
        };
        
        function edit(ev, row){
       	 	vm.loading = true;
       	 	adminUserFactory.edit(
       			 ev,
       			 row,
       			 function(response){
       				vm.loading = false;
                                vm.users = response.data;
       		 		vm.alert('Usuário editado com sucesso ');
       	 		}, function(){
       	 			vm.loading = false;
       	 			vm.alert('Erro ao editar usuário ');
       	 		});
        };
        
        function changePassword(ev, row){
        	vm.loading = true;
       	 	adminUserFactory.changePassword(
       			 ev,
       			 row,
       			 function(response){
       				vm.loading = false;
       		 		vm.alert('Senha editada com sucesso ');
       	 		}, function(){
       	 			vm.alert('Erro ao editar senha');
       	 		});
        }
        
        
        function toChangePassword($event, user){
            $location.path('/admin/user/'+user.id+'/changePassword');
        };
    }
})();


