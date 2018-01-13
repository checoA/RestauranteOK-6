app.controller('ConfiguracionesController', [ '$scope', '$resource', 'configuracionesService','loginService','navegacion', function($scope, $resource, configuracionesService,loginService,navegacion) {

	loginService.comprobar('otros').then(function(datos){
		if(!datos){
			navegacion.setLocation("error");
		}
	})
	
	$scope.configuracionesArray = configuracionesService.getConfiguraciones();
	console.log($scope.configuracionesArray);
	$scope.seleccionado = {};
	
	$scope.obtenerDatos = function(item){
		if(item.id == $scope.seleccionado.id)
		{
			if(!item.esEditable){
				return 't-editar';
			}
			return 't-editar-todo'
		}
		else return 't-normal';
	}
    	
	$scope.editar = function(item) {
        $scope.seleccionado = angular.copy(item);
    };
	
    $scope.guardar = function (id) {
        $scope.configuracionesArray[id] = angular.copy($scope.seleccionado);
        configuracionesService.setConfiguraciones($scope.seleccionado).then(function(datos){
        	if(datos){
        		$scope.configuracionesArray = configuracionesService.getConfiguraciones();
        		$scope.cancelar();
        	}
        })
    };
	
    $scope.agregar = function(){
    	nuevo = {id : "",nombre :"",valor:"",nombreParaMostrar:"",esEditable:true,ticket:true};
    	$scope.configuracionesArray.unshift(nuevo);
    	$scope.seleccionado = angular.copy($scope.configuracionesArray[0]); 
    }
    
    $scope.eliminar = function(){
    	configuracionesService.eliminar($scope.seleccionado).then(function(datos){
    		if(datos){
    			$scope.configuracionesArray = configuracionesService.getConfiguraciones();
        		$scope.cancelar();
    		}
    	})
    }
    
    $scope.cancelar = function () {
        $scope.seleccionado = {};
    };
    
}])
