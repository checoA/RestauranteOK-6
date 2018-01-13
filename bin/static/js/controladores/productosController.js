app.controller('ProductosController', [ '$scope', '$resource', 'productosService','loginService','navegacion', function($scope, $resource, productosService,loginService,navegacion) {
	
	loginService.comprobar('platillos').then(function(datos){
		if(!datos){
			navegacion.setLocation("error");
		}
	})
	
	$scope.inventarioArray = productosService.getInventario();
	$scope.productosArray = productosService.getProductos();
	$scope.producto = {};
	$scope.producto.categoria = {};
	productosService.getCategorias().then(function(datos){
		$scope.categorias= datos;
		$scope.producto.categoria.id = $scope.categorias[0].id;
	})
	
	$scope.temp = [];
	
	$scope.save = function(){
		$scope.producto.producto = $scope.temp;		
		productosService.save($scope.producto).then(function(datos){
			if(datos){
				$scope.productosArray = productosService.getProductos();
				$scope.producto.nombre = "";
				$scope.producto.precio= "";
				$scope.temp=[];
			}
		})
		
	}
	
	$scope.sendToEdit = function(item){
		$scope.producto = item;
		$scope.temp = item.producto;
	}
	
	$scope.eliminarDB = function(item){
		productosService.eliminar(item).then(function(datos){
			if(datos){
				$scope.productosArray = productosService.getProductos();
			}
		})
	}
	
	$scope.agregar = function(item){
		p = {cantidad : 0 ,inventario : item};
		if(obtenerIndice(p) == -1){
			$scope.temp.push(p)
		}
	};
	
	$scope.eliminar = function(item) {	
		$scope.temp.splice(obtenerIndice(item),1)
		productosService.eliminarElemento(item).then(function(datos){
			if(datos){
				//$scope.productosArray = productosService.getProductos();
			}
		})
	}
	
	$scope.filtrar = function(){
		if($scope.filtro == "" || $scope.filtro == undefined){
			$scope.productosArray = productosService.getProductos();
		}else{
			productosService.filtrar($scope.filtro).then(function(datos){
				$scope.productosArray = datos;
			})
		}
	}
	
	function obtenerIndice(elemento){
		respuesta = -1;
		for (var i = 0; i < $scope.temp.length; i++) {
			if(elemento.inventario.codigo == $scope.temp[''+i].inventario.codigo){				
				respuesta = i;
			}
		}
		return respuesta;
	}
	
	
	
}])