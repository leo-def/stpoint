(function(){
    "use strict";
    angular.module('app')
            .factory('appFactory',appFactory);
    
    function appFactory($mdToast, $mdDialog, $mdSidenav){
        var service = {
            name: 'appFactory',
            alert: alert,
            upload:{
                dropZoneStyle: uploadDropZoneStyle
            },
            DialogController: DialogController,
            dialog:{
            	controller : DialogController,
            	customDialog: dialogCustomDialog,
            	simpleDialog: dialogSimpleDialog
            },
            fabToolbar: {
                isOpen: fabToolbarIsOpen,
                count: 0,
                direction: 'left',
                $$isOpen: false
            },
            //sidenav
            sidenav: {
                exists: sidenavExists,
                isOpen: sidenavIsOpen,
                toggle: sidenavToggle,
                setExists : sidenavSetExists,
                $$exists: false 
            },
            //back
            back: {
                exists: backExists,
                execute: backExecute,
                setExecute: backSetExecute,
                removeExecute: backRemoveExecute,
                $$execute: null
            }
        };
        return service;
        
        
        //back
        function backExists(){
            return (service.back.$$execute !== null)
        };
        function backExecute(){
            if(backExists()){
                service.back.$$execute();
            }
        }
        function backSetExecute(execute){
            service.back.$$execute = execute; 
        };
        function backRemoveExecute(){
            service.back.$$execute = null;
        }
        
        //sidenav
        function sidenavExists(){
            return service.sidenav.$$exists;
        };
        
        function sidenavIsOpen(){
            return $mdSidenav('left').isOpen();
        };

        function sidenavToggle(){
            $mdSidenav('left').toggle();
        };
        
        function sidenavSetExists(param){
            service.sidenav.$$exists = param;
        };
        
        //fabToolbar
        function fabToolbarIsOpen(){
            return service.fabToolbar.$$isOpen;
        }
        
        
        //common
        function alert(msg){
            $mdToast.show(
                $mdToast.simple()
                  .textContent(msg)
                  .position('bottom left')
                  .hideDelay(3000)
              );
        }
        
        //upload
        function uploadDropZoneStyle(url){
            if(! url){return "";}
            return  ' background-image: url("'+url+'"); '+
                    ' background-size: 100% 100%; '+
                    ' background-repeat: no-repeat; ';
        }
        
        //dialog
        function dialogCustomDialog(ev, locals, controller, template, onAnswer, onCancel ){
        	if(!controller){
        		controller = DialogController;
        	}
        	return $mdDialog.show({
                locals: locals,
                controller: controller,
                controllerAs: 'vm',
                templateUrl: template,
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true,
                fullscreen: false // Only for -xs, -sm breakpoints.
          }).then(onAnswer, onCancel);
        	
        }
        
        function dialogSimpleDialog(ev, title, content, aria, okTitle, cancelTitle, ok, cancel){
        	return $mdDialog.show(
        			$mdDialog.confirm()
        				.title(title)
        				.textContent(content)
        				.ariaLabel(aria)
        				.targetEvent(ev)
        				.ok(okTitle)
        				.cancel(cancelTitle)
            ).then(ok, cancel);
        }
        
        function DialogController($scope, $mdDialog, row) {
            var vm = this;
            vm.hide = hide;
            vm.cancel = cancel;
            vm.answer = answer;
            vm.row = row;
           
            function hide() {
                $mdDialog.hide();
            };

            function cancel() {
                $mdDialog.cancel();
            };

            function answer(panswer) {
                $mdDialog.hide(panswer);
            };
        };

    }
})();

