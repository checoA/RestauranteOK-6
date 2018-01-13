app.factory("productosService", [ '$location', '$resource','$q','orden_mesa',
	'alerts', 'urls',
	function($location, $resource,$q,orden_mesa, alerts, urls) {
		return{
			getInventario:function(){
				return $resource(urls.servidor+"obtenerInventario",{},{
					query:{
						method : 'GET',
						isArray:true
					}
				}).query(function success(response){
					return response;
				})
			},
			getProductos:function(){
				return $resource(urls.servidor+"productos",{},{
					query:{
						method : 'GET',
						isArray:true
					}
				}).query(function success(response){
					return response;
				})
			},
			getCategorias:function(){
				return $resource(urls.servidor+"getCategorias",{},{
					query:{
						method:'GET',
						isArray:true
					}
				}).query().$promise.then(function success(response){
					console.log(response)
					return response;
				})
			},
			save:function(producto){
				return $resource(urls.servidor+"guardarProducto",{},{
					save:{
						method:'PUT',
						isArray:false
					}
				}).save(producto).$promise.then(function success(response){
					return response.created
				})
			},
			eliminar:function(producto){
				return $resource(urls.servidor+"eliminarPlatillo",{},{
					save:{
						method:'PUT',
						isArray:false
					}
				}).save(producto).$promise.then(function success(response){
					return response.deleted;
				})
			},
			eliminarElemento:function(elemento){
				return $resource(urls.servidor+"eliminarElemento",{},{
					save:{
						method:'PUT',
						isArray:false
					}
				}).save(elemento).$promise.then(function success(response){
					return response.deleted;
				})
			},
			filtrar:function(filtro){
				console.log(filtro)
				return $resource(urls.servidor+"filtrar",{},{
					query:{
						method:'PUT',
						isArray:true
					}
				}).query(filtro).$promise.then(function success(response){
					return response;
				})
			}
		}
	}])