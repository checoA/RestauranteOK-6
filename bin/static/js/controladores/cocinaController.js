app.controller('CocinaController', [ '$scope','$rootScope', '$resource', 'cocinaService','loginService','navegacion','messagesService','notificaciones','$timeout', function($scope,$rootScope, $resource, cocinaService,loginService,navegacion,messagesService,notificaciones,$timeout) {
	
	loginService.comprobar('cocina').then(function(datos){
		if(!datos){
			navegacion.setLocation("error");
		}
	})
	
	notificaciones.unsetCocina();
	
	//$scope.chat = messagesService('/rest');
	
	$rootScope.ordenes=cocinaService.obtener_ordenes();
	$scope.mesa =0;
	$scope.orden = 0;
	var dialog = document.querySelector('dialog');
    var showDialogButton = document.querySelector('.show-dialog');
    if (! dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
      }
    
	$scope.mostrarOrden=function(id,mesa){
		$rootScope.chat.send("/app/unsetCocinaAlert",{},{});
		cocinaService.obtenerDetalles(id).then(function(datos){
			$scope.productosOrden = datos;
			$scope.mesa = mesa;
			$scope.orden = id;
			dialog.showModal();
			$timeout(function(){
				cocinaService.setVisto(id);
			},1000);
		})
	}
	
	cocinaService.getTonos().then(function(datos){
		$scope.tonos_array = datos;
		console.log(cocinaService.getTono())
		cocinaService.getTono().then(function(tono){
			$scope.tonos = tono;
		});
	})
	
	$scope.setTono=function(){
		cocinaService.setTono($scope.tonos).then(function(datos){
			if(datos){
				cocinaService.getTonos().then(function(tonos){
					$scope.tonos_array = tonos;
					console.log(cocinaService.getTono())
					cocinaService.getTono().then(function(tono){
						$scope.tonos = tono;
					});
				})
			}
		})
	}
	
	$scope.entregar=function(){
		cocinaService.entregar($scope.mesa,$scope.orden).then(function(datos){
			if(datos.entregar){
				$rootScope.chat.send("/app/setEntregar", {}, JSON.stringify(datos));
				
			}
			dialog.close();
		})
	}
	
	$scope.entregarProducto=function(item){
		cocinaService.entregarProducto($scope.mesa,item).then(function(datos){
			dialog.close();
			if(datos.entregar){
				$rootScope.chat.send("/app/setEntregar",{},JSON.stringify(datos));
			}			
		})
	}
	
	dialog.querySelector('.close').addEventListener('click',function(){
		$rootScope.ordenes=cocinaService.obtener_ordenes();
		dialog.close();
	})
}])