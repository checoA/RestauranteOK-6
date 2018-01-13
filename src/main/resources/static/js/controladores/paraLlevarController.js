app.controller('ParaLlevarController', [ '$scope','$rootScope', '$resource', 'paraLlevarService','loginService','navegacion','messagesService','notificaciones','$timeout', function($scope,$rootScope, $resource, paraLlevarService,loginService,navegacion,messagesService,notificaciones,$timeout) {
	
	loginService.comprobar('mesas').then(function(datos){
		if(!datos){
			navegacion.setLocation("error");
		}
	})
	
	$scope.habilitado =false;
	
	notificaciones.unsetEntregar();
	
	paraLlevarService.recuperar_mesas().then(function(datos){
		$rootScope.paraLlevarArray = datos;
	});
	
	//$scope.chat = messagesService('/rest');
	
	$scope.ordenar = function(id,b,e){
		paraLlevarService.ordenar(id,b,e);
	}
	
	$scope.add= function(){
		$rootScope.chat.send("/app/addParaLlevar",{},{});
		$scope.habilitado = true;
		$timeout(function(){$scope.habilitado = false;},1000);
	}
	$scope.del= function(){
		$rootScope.chat.send("/app/delParaLlevar",{},{});	
	}
	$scope.entregado=function(id){
		paraLlevarService.entregado(id).then(function(datos){
			if(datos.entregado){
				$rootScope.chat.send("/app/setEntregar", {}, JSON.stringify(datos));
				$rootScope.chat.send("/app/unsetEntregarAlert",{},{});
				//$scope.mesaArray = mesasService.recuperar_mesas();
			}
		})
	}
	
	
}]);