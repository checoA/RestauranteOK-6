app.factory("cocinaService", [ '$location', '$resource','$q',
	'alerts','notificaciones' ,'urls',
	function($location, $resource,$q, alerts,notificaciones, urls) {
	return{
		obtener_ordenes:function(){
			return $resource(urls.servidor+"ordenes",{},{
				query:{
					method:'GET',
					isArray:true
				}
			}).query(function success(response){
				console.log(response)
				return response;
			})
		},
		obtenerDetalles:function(id){
			return $resource(urls.servidor+"ordenDetalles",{},{
				query:{
					method:'PUT',
					isArray:true
				}
			}).query(id).$promise.then(function success(response){
				return response;
			})
		},
		setVisto:function(id){
			return $resource(urls.servidor+"setVisto",{},{
				save:{
					method:'PUT',
					isArray:false
				}
			}).save(id).$promise.then(function success(response){
				
			})
		},
		entregar:function(id,id_or){
			return $resource(urls.servidor+"entregar",{},{
				save:{
					method:'PUT',
					isArray:false
			}
			}).save(id).$promise.then(function success(response){
				if(response.entregar){
					notificaciones.setEntregar();
				}
				return response;
			},function error(data){
				console.log(data)
			})
		},
		entregarProducto:function(id,item){
			return $resource(urls.servidor+"entregarProducto/:id",{},{
				save:{
					method:'PUT',
					isArray:false,
					params:{id:'@id'}
				}
			}).save({id :id},item).$promise.then(function success(response){
				if(response.entregar){
					notificaciones.setEntregar();
				}
				return response;
			})
		},
		getTonos:function(){
			return $resource(urls.servidor+"getTonos",{},{
				query:{
					method:'GET',
					isArray:true
				}
			}).query().$promise.then(function success(response){
				return response;
			})
		},
		getTono:function(){
			return $resource(urls.servidor+"getTono",{},{
				query:{
					method:'GET',
					isArray:false
				}
			}).query().$promise.then(function success(response){
				return response;
			})
		},
		setTono:function(tono){
			return $resource(urls.servidor+"setTono",{},{
				save:{
					method:'PUT',
					isArray:false
				}
			}).save(tono).$promise.then(function success(response){
				return response.created;
			})
		}
	}
}])