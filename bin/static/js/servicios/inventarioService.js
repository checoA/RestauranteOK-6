app.factory("inventarioService", [ '$location', '$resource','$q','orden_mesa','notificaciones',
	'alerts', 'urls',
	function($location, $resource,$q,orden_mesa, notificaciones,alerts, urls) {
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
			setInventario:function(inventario){
				return $resource(urls.servidor+"guardarInventario",{},{
					save:{
						method:'PUT',
						isArray:false
					}
				}).save(inventario).$promise.then(function(response){
					return response.created;
				})
			},
			eliminar:function(inventario){
				return $resource(urls.servidor+"eliminarInventario",{},{
					save:{
						method:'PUT',
						isArray:false
					}
				}).save(inventario).$promise.then(function(response){
					return response.deleted;
				})
			}
		}
	}])