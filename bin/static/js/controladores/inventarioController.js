app.controller('InventarioController', [ '$scope', '$resource', 'inventarioService','loginService','navegacion', function($scope, $resource, inventarioService,loginService,navegacion) {
	
	loginService.comprobar('inventario').then(function(datos){
		if(!datos){
			navegacion.setLocation("error");
		}
	})
	
	$scope.inventarioArray = inventarioService.getInventario();
	$scope.seleccionado = {};
	
	$scope.obtenerDatos = function(item){
		if(item.codigo == $scope.seleccionado.codigo)
			return 't-editar';
		else return 't-normal';
	}
    	
	$scope.editar = function(item) {
        $scope.seleccionado = angular.copy(item);
    };
    
    $scope.guardar = function (id) {
        $scope.inventarioArray[id] = angular.copy($scope.seleccionado);
        inventarioService.setInventario($scope.seleccionado).then(function(datos){
        	if(datos){
        		$scope.inventarioArray = inventarioService.getInventario();
        		$scope.cancelar();
        	}
        })
        console.log($scope.seleccionado)
    };
	
    $scope.agregar = function(){
    	nuevo = {codigo : "",nombre :"",precio:"",existencia:"",stock:""}
    	$scope.inventarioArray.unshift(nuevo);
    	$scope.seleccionado = angular.copy($scope.inventarioArray[0]); 
    }
    
    $scope.eliminar = function(){
    	inventarioService.eliminar($scope.seleccionado).then(function(datos){
    		if(datos){
    			$scope.inventarioArray = inventarioService.getInventario();
        		$scope.cancelar();
    		}
    	})
    }
    
    $scope.cancelar = function () {
        $scope.seleccionado = {};
    };
    
}])