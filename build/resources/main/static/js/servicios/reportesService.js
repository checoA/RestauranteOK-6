app.factory("reportesService", [ '$location', '$resource','$q',
	'alerts', 'urls','fechaReal',
	function($location, $resource,$q, alerts, urls,fechaReal) {
	return{
		getReporteVentas:function(reporte){
			hoy = fechaReal.getFechaReal(reporte.fecha_hoy);
		    manana = fechaReal.getFechaReal(reporte.fecha_manana);
			return $resource(urls.servidor+"reporteVentas/:fecha_fin",{},{
				query:{
					method:'PUT',
					params:{fecha_fin:'@fecha_fin'},
					isArray:true
				}
			}).query({fecha_fin:manana},hoy).$promise.then(function success(response){
				return response;
			})
		},
		getReporteVentasFiltro:function(reporte){
			hoy = fechaReal.getFechaReal(reporte.fecha_hoy);
		    manana = fechaReal.getFechaReal(reporte.fecha_manana);
		    console.log(reporte.tipo_venta+"tipo_venta")
			return $resource(urls.servidor+"reportesVentasFiltro/:fecha_fin/:filtro",{},{
				query:{
					method:'PUT',
					params:{fecha_fin:'@fecha_fin',filtro:'@filtro'},
					isArray:true
				}
			}).query({fecha_fin:manana,filtro:reporte.tipo_venta},hoy).$promise.then(function success(response){
				return response;
			})
		},
		getReporteGastos:function(reporte){
			hoy = fechaReal.getFechaReal(reporte.fecha_hoy);
		    manana = fechaReal.getFechaReal(reporte.fecha_manana);
			return $resource(urls.servidor+"reporteGastos/:fecha_fin",{},{
				query:{
					method:'PUT',
					params:{fecha_fin:'@fecha_fin'},
					isArray:true
				}
			}).query({fecha_fin:manana},hoy).$promise.then(function success(response){
				return response;
			})		
		},
		getReporteGastosFiltro:function(reporte){
			hoy = fechaReal.getFechaReal(reporte.fecha_hoy);
		    manana = fechaReal.getFechaReal(reporte.fecha_manana);
			return $resource(urls.servidor+"reporteGastosFiltro/:fecha_fin/:filtro",{},{
				query:{
					method:'PUT',
					params:{fecha_fin:'@fecha_fin',filtro:'@filtro'},
					isArray:true
				}
			}).query({fecha_fin:manana,filtro:reporte.tipo_gasto},hoy).$promise.then(function success(response){
				return response;
			})
		},
		getMasVendidos:function(reporte){
			console.log(reporte)
		    hoy = fechaReal.getFechaReal(reporte.fecha_hoy);
		    manana = fechaReal.getFechaReal(reporte.fecha_manana);
			return $resource(urls.servidor+"masVendidos/:fecha_inicio/:fecha_fin/:categoria",{},{
				query:{
					method:'PUT',
					params:{fecha_inicio:'@fecha_inicio',fecha_fin:'@fecha_fin',categoria:'@categoria'},
					isArray:true
				}
			}).query({fecha_inicio:hoy,fecha_fin:manana,categoria:reporte.categoria},function success(response){
				console.log(response)
				return response;
			})
		},
		getTiposVentas:function(){
			return $resource(urls.servidor+"tipos_pago",{},{
				query:{
					method:'GET',
					isArray:true
				}
			}).query().$promise.then(function success(response){
				return response;
			})
		},
		getTiposGastos:function(){
			return $resource(urls.servidor+"getTG",{},{
				query:{
					method:'GET',
					isArray:true
				}
			}).query().$promise.then(function success(response){
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
		getDO:function(orden){
			return $resource(urls.servidor+"obtenerDO",{},{
				query:{
					method:'PUT',
					isArray:true,
				}
			}).query(orden).$promise.then(function success(response){
				return response;
			})
		},
		getCategorias:function(){
			return $resource(urls.servidor+"getCategorias",{},{
				query:{
					method:'GET',
					isArray:true
				}
			}).query().$promise.then(function(response){
				console.log(response)
				return response;
			})
		}
	}
}])