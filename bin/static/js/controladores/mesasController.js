app.controller('MesasController', [ '$scope','$rootScope', '$resource', 'mesasService','loginService','navegacion','messagesService','notificaciones','$timeout', function($scope,$rootScope, $resource, mesasService,loginService,navegacion,messagesService,notificaciones,$timeout) {
	
	loginService.comprobar('mesas').then(function(datos){
		if(!datos){
			navegacion.setLocation("error");
		}
	})
	
	$scope.habilitado =false;
	
	notificaciones.unsetEntregar();
	
	
	mesasService.recuperar_mesas().then(function(datos){
		$rootScope.mesaArray = datos;
	});
	
	//$scope.chat = messagesService('/rest');
	
	$scope.ordenar = function(id,b,e){
		mesasService.ordenar(id,b,e);
		$rootScope.chat.send("/app/unsetEntregarAlert",{},{});
	}
	
	$scope.add= function(){
		$rootScope.chat.send("/app/add",{},{});
		$scope.habilitado = true;
		$timeout(function(){$scope.habilitado = false;},1000);
	}
	$scope.del= function(){
		$rootScope.chat.send("/app/del",{},{});	
	}
	$scope.entregado=function(id){
		mesasService.entregado(id).then(function(datos){
			if(datos.entregado){
				$rootScope.chat.send("/app/setEntregar", {}, JSON.stringify(datos));
				$rootScope.chat.send("/app/unsetEntregarAlert",{},{});
				//$scope.mesaArray = mesasService.recuperar_mesas();
			}
		})
	}
	
	
}]);