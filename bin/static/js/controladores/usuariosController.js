app.controller('UsuariosController', [ '$scope', '$resource', 'usuariosService','loginService','navegacion', function($scope, $resource, usuariosService,loginService,navegacion) {
	
	loginService.comprobar('usuarios').then(function(datos){
		if(!datos){
			navegacion.setLocation("error");
		}
	})
	
	$scope.usuario = {};
	var dialog = document.querySelector('dialog');
    var showDialogButton = document.querySelector('.show-dialog');
    if (! dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
      }
    
	$scope.usuariosArray = usuariosService.usuarios();
	
	$scope.guardar = function(item){
		usuariosService.save(item).then(function(datos){
			if(datos){
				$scope.usuariosArray = usuariosService.usuarios();
			}
		});
	}
	
	$scope.eliminar = function(item){
		usuariosService.del(item).then(function(datos){
			if(datos){
				$scope.usuariosArray = usuariosService.usuarios();
			}
		})
	}
	
	$scope.editar = function(item){
		$scope.usuario = item;
		$scope.usuario.password = "";
		dialog.showModal();
	}
	
	$scope.actualizar = function(){
		usuariosService.actualizar($scope.usuario).then(function(datos){
			if(datos){
				$scope.usuariosArray = usuariosService.usuarios();
				dialog.close();
			}
		})
	}
	
	dialog.querySelector('.close').addEventListener('click',function(){
		dialog.close();
		$scope.usuario={};
	})
	
}])