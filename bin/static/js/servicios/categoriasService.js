app.factory("categoriasService", [ '$location', '$resource','$q',
	'alerts', 'urls',
	function($location, $resource,$q,alerts, urls) {
		return{
			getCategorias:function(){
				return $resource(urls.servidor+"getCategorias",{},{
					query:{
						method:'GET',
						isArray:true
					}
				}).query(function success(response){
					return response;
				})
			},
			saveCategorias:function(categoria){
				return $resource(urls.servidor+"saveCategorias",{},{
					save:{
						method:'PUT',
						isArray:false
					}
				}).save(categoria).$promise.then(function success(response){
					return response.created;
				})
			},
			deleteCategorias:function(categoria){
				return $resource(urls.servidor+"deleteCategorias",{},{
					save:{
						method:'PUT',
						isArray:false
					}
				}).save(categoria).$promise.then(function success(response){
					return response.deleted;
				})
			}
		}	
}])