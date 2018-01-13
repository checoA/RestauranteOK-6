app.factory("loginService", [ '$location', '$resource','$q', 'sesionesControl','alterMenu',
	'alerts', 'urls',
	function($location, $resource,$q, sesionesControl,alterMenu, alerts, urls) {
		var cacheSession = function(datos) {
			sesionesControl.set("usuarioLogin", true);
			var usuario = {
				nombre : datos.usuario.nombre,
				password : datos.usuario.password,
				mensaje : "",
				permisos: datos.permisos
			};
			sesionesControl.setList("usuario", usuario);
		}
		var unCacheSession = function() {
			sesionesControl.unset("usuarioLogin");
			sesionesControl.unset("usuario");
		}

		return {
			login : function(usuario) {
				alerts.clearLogin()
				if (usuario.password == "" || usuario.nombre == "") {
					return $q.reject(false);
				} else {
					User = $resource(urls.servidor + "login", {}, {
						query : {
							method : 'PUT',
							isArray : false
						}
					});
					usuario.password = ""+CryptoJS.SHA512(usuario.password)
					return User.query(usuario).$promise.then(function success(response) {						
						if (response.login) {
							cacheSession(response);
							sesionesControl.unset("mensaje");
							alterMenu.setMenu(response.permisos)
							$location.path('/listado');
						} else {
							alerts.showLogin(response.mensaje, "danger");
						}
						return response.login
					}, function error(response, status) {
						alerts.showLogin(response.mensaje, "danger");
						return $q.reject(false);
					})
				}
			},
			exist:function(user){
				alerts.clearAccount()
				return $resource(urls.servidor+"exist",{},{
					query:{
						method:'PUT',
						isArray:false
					}				
				}).query(user).$promise.then(function success(response){
					if(response.exist){
						alerts.showAccount(response.mensaje,"danger")
					}
					return response.exist;
				})
			},
			crear_cuenta : function(user) {
				alerts.clearAccount()
				if(user.user && user.password && user.confirm_password && user.nombre && user.apepat && user.apemat && user.correo != "" )
				if(user.password == user.confirm_password){
					User = $resource(urls.servidor + "save/", {}, {
						save : {
							method : 'PUT',
						}
					});
					user.password = user.confirm_password = "" + CryptoJS.SHA512(user.password);
					return User.save(user).$promise.then(function success(response){
						if(!response.created){
							alerts.showAccount(response.mensaje, "danger");
						}
						return response.created
					},function error(response){
						alerts.showAccount(response.mensaje, "danger");
						return $q.reject(false);
					})					
				}else{
					alerts.showAccount("Las contraseñas no coinsiden", "danger");
					return $q.reject(false);
				}
			},
			logout : function() {
				sesionesControl.clear();
				sesionesControl.setList("mensaje", {
					texto : "¡Hasta pronto!",
					tipo : "success"
				});
				alterMenu.setMenu(undefined)
				$location.path("/login");
			},
			obtener_cuentas: function(){
				User = $resource(urls.servidor + "cuentas");
				return User.query(function success(response){
					return response;
				},function error(response){
					
				})
			},
			comprobar:function(accion){
				if(sesionesControl.getList("usuario") == null){
					$location.path("/login");
				}else{
					nombre = sesionesControl.getList("usuario").nombre;
					return $resource(urls.servidor+"comprobar/:nombre",{},{
						query:{
							method:'PUT',
							params: {nombre:'@nombre'},
							isArrar:false
						}
					}).query({nombre:nombre},accion).$promise.then(function success(response){
						return response.created;
					})
				}
			}
		}
	} ]);