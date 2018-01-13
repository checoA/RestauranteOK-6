app.controller('CategoriasController', [ '$scope', '$resource', 'categoriasService', function($scope, $resource, categoriasService) {
	$scope.categorias={};
	$scope.categoriasArray = categoriasService.getCategorias();
	
	$scope.guardar = function(){
		categoriasService.saveCategorias($scope.categorias).then(function(datos){
			if(datos){
				$scope.categoriasArray = categoriasService.getCategorias();
			}
		})
	}
	
	$scope.eliminar = function(item){
		categoriasService.deleteCategorias(item).then(function(datos){
			if(datos){
				$scope.categoriasArray = categoriasService.getCategorias();
			}
		})
	}
	
	$scope.editar = function(item){
		$scope.categorias = item;
	}
	
}])