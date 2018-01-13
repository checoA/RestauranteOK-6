app.factory("cajaService", [ '$location', '$resource','$q',
	'alerts', 'urls',
	function($location, $resource,$q, alerts, urls) {
	return{
		obtenerCaja:function(){
			return $resource(urls.servidor+"caja",{},{
				query:{
					method:'GET',
					isArray:true
				}				
			}).query().$promise.then(function success(response){
				return response;
			})
		},
	obtenerTiposPago:function(){
		return $resource(urls.servidor+"tipos_pago",{},{
			query:{
				method:'GET',
				isArray:true
			}
		}).query(function success(response){
			return response;
		})
	},
	obtenerProductos:function(id){
		return $resource(urls.servidor+"ordenDetalles",{},{
				query:{
					method:'PUT',
					isArray:true
				}
			}).query(id).$promise.then(function success(response){
				return response;
			})
		},
	getEmpresa:function(){
		return $resource(urls.servidor+"empresa",{},{
			query:{
				method:'GET',
				isArray:false
			}				
			}).query().$promise.then(function success(response){
				return response;
			})
		},
		concretarVenta:function(tipo_pago){
			return $resource(urls.servidor+"concretarVenta",{},{
				save:{
					method:'PUT',
					isArray:false
				}
			}).save(tipo_pago).$promise.then(function success(response){
				return response;
			})
		},
		concretarVentaParaLlevar:function(orden){
			console.log(orden)
			return $resource(urls.servidor+"concretarVentaParaLlevar",{},{
				save:{
					method:'PUT',
					isArray:false
				}
			}).save(orden).$promise.then(function success(response){
				return response;
			})
		},
		obtenerAumentos:function(nombre){
			return $resource(urls.servidor+"getConfiguracion",{},{
				query:{
					method:'PUT',
					isArray:false
				}
			}).query(nombre).$promise.then(function(response){
				return response;
			})
		},
		obtenerDetalle:function(orden){
			return $resource(urls.servidor+"obtenerDO",{},{
				query:{
					method:'PUT',
					isArray:true
				}
			}).query(orden).$promise.then(function (response){
				return response;
			})
		},
		liberar:function(orden){
			$resource(urls.servidor+"liberar",{},{
				save:{
					method:'PUT',
					isArray:false
				}
			}).save(orden,function success(response){
				return response;
			})
		}
	}
}])