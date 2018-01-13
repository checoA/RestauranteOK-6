app.factory("configuracionesService", [ '$location', '$resource','$q','orden_mesa','notificaciones',
	'alerts', 'urls',
	function($location, $resource,$q,orden_mesa, notificaciones,alerts, urls) {
	return{
		getConfiguraciones:function(){
			return $resource(urls.servidor + "getConfiguraciones",{},{
				query:{
					method:'GET',
					isArray:true
				}
			}).query(function(response){
				return response;
			})
		},
		setConfiguraciones:function(config){
			return $resource(urls.servidor + "setConfiguracion",{},{
				save:{
					method:'PUT',
					isArray:false
				}
			}).save(config).$promise.then(function(response){
				return response.created;
			})
		}
	}
}])