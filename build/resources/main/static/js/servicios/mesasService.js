app.factory("mesasService", [ '$location', '$resource','$q','orden_mesa',
	'alerts', 'urls',
	function($location, $resource,$q,orden_mesa, alerts, urls) {
	return{
		recuperar_mesas: function(){
			return $resource(urls.servidor+"mesas",{},{
				query:{
					method:'GET',
					isArray:true
				}				
			}).query().$promise.then(function success(response){
				return response;
			})
		},
		ordenar:function(id,b,e){
			orden_mesa.setNumeroMesa(id);
			orden_mesa.setBandera(b);
			orden_mesa.setEstado(e);
			$location.path("/barra");
		},
		add:function(){
			return $resource(urls.servidor+'add').save().$promise.then(function success(response){
				return response.mesaCreated;
			});
		},
		del:function(){
			return $resource(urls.servidor+'del').delete().$promise.then(function succes(response){
				return response.deleted;
			})
		},
		entregado:function(id){
			return $resource(urls.servidor+"entregado",{},{save:{method:'PUT',isArray:false}}).save(id).$promise.then(function success(response){
				return response;
			})
		}
	}
}]);