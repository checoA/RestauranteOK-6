app.controller('EmpresaController', [ '$scope', '$resource', 'empresaService','loginService','navegacion', function($scope, $resource, empresaService,loginService,navegacion) {
	
	loginService.comprobar('empresa').then(function(datos){
		if(!datos){
			navegacion.setLocation("error");
		}
	})
	
	$scope.empresa = empresaService.getEmpresa();
	

	
	$scope.save = function(){
		empresaService.setEmpresa($scope.empresa).then(function (datos){
			if(datos){
				$scope.empresa = empresaService.getEmpresa();
			}
		});
	}
}])