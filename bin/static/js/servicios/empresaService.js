app.factory("empresaService", [ '$location', '$resource','$q',
	'alerts', 'urls',
	function($location, $resource,$q, alerts, urls) {
	return{
		getEmpresa:function(){
			return $resource(urls.servidor+"empresa",{},{
				query:{
					method:'GET',
					isArray:false
				}				
			}).query(function success(response){
				return response;
			})
		},
		setEmpresa:function(empresa){
			empresa.id = 1;
			return $resource(urls.servidor+"saveEmpresa",{},{
				save:{
					method:'PUT',
					isArray:false
				}
			}).save(empresa).$promise.then(function success(response){
				return response.created;
			})
		}
	}
}])