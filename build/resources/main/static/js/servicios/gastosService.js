app.factory("gastosService", [ '$location', '$resource','$q',
	'alerts', 'urls',
	function($location, $resource,$q, alerts, urls) {
	return{
		setG:function(gasto){
			return $resource(urls.servidor+"setG",{},{
				save:{
					method:'PUT',
					isArray:false
				}
			}).save(gasto).$promise.then(function success(response) {
				return response.created;
			})
		},
		getTG:function() {
			return $resource(urls.servidor+"getTG",{},{
				query:{
					method:'GET',
					isArray:true
				}
			}).query(function success(response){
				return response;
			})
		},
		setTG:function(tipo_producto){
			tipo_producto.id = "";
			return $resource(urls.servidor+"setTG",{},{
				save:{
					method:'PUT',
					isArray:false
				}
			}).save(tipo_producto).$promise.then(function success(response){
				return response.created;
			})
		},
		deleteTG:function(tipo_producto){
			return $resource(urls.servidor+"deleteTG",{},{
				save:{
					method:'PUT',
					isArray:false
				}
			}).save(tipo_producto).$promise.then(function success(response){
				return response.deleted;
			})
		}
	}
}])