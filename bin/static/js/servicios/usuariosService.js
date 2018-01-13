app.factory("usuariosService", [ '$location', '$resource','$q',
	'alerts','notificaciones' ,'urls',
	function($location, $resource,$q, alerts,notificaciones, urls) {
	return{
		usuarios:function(){
			User = $resource(urls.servidor + "Users");
			return User.query(function success(response){
				return response;
			},function error(response){
				
			})
		},
		save:function(user){
			User = $resource(urls.servidor + "saveROL", {}, {
				save : {
					method : 'PUT',
					isArray:false
				}
			});
			return User.save(user).$promise.then(function success(response){
				return response.created
			},function error(response){
				return $q.reject(false);
			})					
		},
		del:function(user){
			return $resource(urls.servidor+"deleteUser",{},{
				save:{
					method:'PUT',
					isArray:false
				}
			}).save(user).$promise.then(function success(response){
				return response.deleted;
			})
		},
		actualizar:function(user){
			user.password = user.confirm_password = "" + CryptoJS.SHA512(user.password);
			return $resource(urls.servidor+"updateUser",{},{
				save:{
					method:'PUT',
					isArrar:false
				}
			}).save(user).$promise.then(function success(response){
				return response.updated;
			})
		}
	}
}])