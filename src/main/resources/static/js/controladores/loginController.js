app.controller('LoginController', [ '$scope', '$resource', 'loginService', function($scope, $resource, loginService) {
	$scope.login = {
		nombre : "",
		password : ""
	};
	
	loginService.logout();
	
	$scope.cuentas = loginService.obtener_cuentas();
	$scope.cuentas.$promise.then(function(data) {
		$scope.account = {
			permiso : data[0]
		}
	})

	$scope.iniciar_sesion = function() {
		loginService.login($scope.login).then(function(datos) {
			if (datos) {
				$scope.login = {};
			} else {
				$scope.login = {
					nombre : $scope.login.nombre,
					password : ""
				}
			}
			$scope.loginForm.$setPristine();
			$scope.loginForm.$setUntouched();
		})
	}

	$scope.exist = function(){
		loginService.exist($scope.account.user).then(function(datos){
			if(datos){
				$scope.account={user : "",
					password : $scope.account.nombre,
					confirm_password : $scope.account.nombre,
					nombre : $scope.account.nombre,
					apepat : $scope.account.apepat,
					apemat : $scope.account.apemat,
					correo : $scope.account.correo}
			}
			$scope.accountForm.$setPristine();
			$scope.accountForm.$setUntouched();
		})
	}
	
	$scope.crear_cuenta = function() {
		loginService.crear_cuenta($scope.account).then(function(datos) {
			if (datos) {
				$scope.account = {}
				$scope.varizq = 'animate-izq-detras-regresar'
				$scope.var1 = 'animate-izq-regresar'
			} else {
				$scope.account = {
					user : $scope.account.user,
					password : "",
					confirm_password : "",
					nombre : $scope.account.nombre,
					apepat : $scope.account.apepat,
					apemat : $scope.account.apemat,
					correo : $scope.account.correo
				}
				$scope.varizq = 'animate-izq-detras-regresar'
					$scope.var1 = 'animate-izq-regresar'
			}
			$scope.accountForm.$setPristine();
			$scope.accountForm.$setUntouched();
		})
	}
/*
	$scope.logout = function() {
		loginService.logout();
	};*/
} ]);