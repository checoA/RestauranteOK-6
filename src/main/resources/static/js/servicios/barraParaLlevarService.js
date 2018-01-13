app.factory("barraParaLlevarService", [ '$location', '$resource','$q','orden_mesa','notificaciones',
	'alerts', 'urls','sesionesControl',
	function($location, $resource,$q,orden_mesa, notificaciones,alerts, urls,sesionesControl) {
	return{
		getMesa:function(){
			return orden_mesa.getNumeroMesa();
		},
		recuperarOrden:function(){
			mesa = orden_mesa.getNumeroMesa();
			estado = orden_mesa.getEstado();
			if(estado==0){
			return $resource(urls.servidor+"getOrdenParaLlevar",{},{
				query:{
					method:'PUT',
					isArray:true
				}
			}).query(mesa).$promise.then(function success(response){
				return response;
			},function error(response){
				return [];
			})
			}else{
				return $resource(urls.servidor+"getOrdenEstadoParaLlevar/:estado",{},{
					query:{
						method:'PUT',
						params:{estado:'@estado'},
						isArray:true
					}
				}).query({estado:estado.id},mesa).$promise.then(function success(response){
					return response;
				})
			}
		},
		obtener_productos:function(){
			return $resource(urls.servidor+"productos",{},{
				query:{
					method:'GET',
					isArray:true
				}
			}).query(function success(response){
				return response;
			})
		},
		obtener_x_id:function(id){
			return $resource(urls.servidor+"por_id",{},{
				query:{
					method:'PUT',
					isArray:false
				}
			}).query(id).$promise.then(function success(response){
				
				return response;
			})
		},
		enviarVentas:function(productos,numClientes){
			estado = orden_mesa.getEstado();
			if(estado.id == undefined){
			estado = {};
			estado.id = 0;
		}
			Obj = $resource(urls.servidor+"saveDO/:num_mesa/:bandera/:estado/:numClientes/:usuario",{},{
				save:{
					method:'PUT',
					params:{num_mesa:'@num_mesa',bandera:'@bandera',estado:'@estado',numClientes:'@numClientes',usuario:'@usuario'}
				}
			})
			mesa = orden_mesa.getNumeroMesa();
			b = orden_mesa.getBandera();
			
			return Obj.save({num_mesa:mesa,bandera : b,estado:estado.id,numClientes:numClientes,usuario:sesionesControl.getList("usuario").nombre},productos).$promise.then(function success(response){
				if(response.created){
					notificaciones.setCocina();
				}
				return response;
			})
		},
		enviarVentasParaLlevar:function(productos,numClientes){
			estado = orden_mesa.getEstado();
			if(estado.id == undefined){
				estado = {};
				estado.id = 0;
			}
			Obj = $resource(urls.servidor+"saveDOParaLlevar/:num_mesa/:bandera/:estado/:numClientes/:usuario",{},{
				save:{
					method:'PUT',
					params:{num_mesa:'@num_mesa',bandera:'@bandera',estado:'@estado',numClientes:'@numClientes',usuario:'@usuario'}
				}
			})
			mesa = orden_mesa.getNumeroMesa();
			b = orden_mesa.getBandera();
			return Obj.save({num_mesa:mesa,bandera : b,estado:estado.id,numClientes:numClientes,usuario:sesionesControl.getList("usuario").nombre},productos).$promise.then(function success(response){				
				return response;
			})
		},
		eliminarProducto:function(producto){
			return $resource(urls.servidor+"eliminarProducto",{},{
				save:{
					method:"PUT",
					isArray:false
				}
			}).save(producto).$promise.then(function success(response){
				return response.created;
			})
		},
		cancelarVenta:function(productos){
			mesa = orden_mesa.getNumeroMesa();
			return $resource(urls.servidor+"cancelarVentaParaLlevar/:num_mesa",{},{
				save:{
					method:"PUT",
					params:{num_mesa:'@num_mesa'}
				}
			}).save({num_mesa:mesa},productos).$promise.then(function success(response){
				return response.created;
			})
		},
		obtenerOrden:function(id){
			return $resource(urls.servidor+"ordenById",{},{
				query:{
					method:'PUT',
					isArray:false
				}
			}).query(id).$promise.then(function success(response){
				return response;
			})
		},
		liberarParaLlevar:function(orden){
			$resource(urls.servidor+"liberarParaLlevar",{},{
				save:{
					method:'PUT',
					isArray:false
				}
			}).save(orden,function success(response){
				return response;
			})
		}
	}
}]);