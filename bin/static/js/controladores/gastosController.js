app.controller('GastosController', [ '$scope', '$resource', 'gastosService','loginService','navegacion', function($scope, $resource, gastosService,loginService,navegacion) {
	
	loginService.comprobar('gastos').then(function(datos){
		if(!datos){
			navegacion.setLocation("error");
		}
	})
	
	$scope.tipoGastoArray = gastosService.getTG();
	$scope.gasto = {};
	$scope.concepto = {};
	
	f = new Date();
	
	$scope.concepto.fecha = f; 
	
	$scope.setG=function(){
		gastosService.setG($scope.concepto).then(function(datos){
			if(datos){
				$scope.concepto = {};
				$scope.concepto.fecha = f; 
			}
		})
	}
	
	$scope.setTG=function(){
		gastosService.setTG($scope.gasto).then(function(datos){
			if(datos){
				$scope.tipoGastoArray = gastosService.getTG();
			}
		})
	}
	
	$scope.deleteTG=function(item){
		gastosService.deleteTG(item).then(function(datos){
			if(datos){
				$scope.tipoGastoArray = gastosService.getTG();
			}
		})
	}
}])