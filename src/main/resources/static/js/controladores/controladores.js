'use strict';

// controlador principal
app.controller('controladorPrincipal', [ '$rootScope', '$scope', 'sesionesControl', 'alerts','navegacion','loginService','messagesService','mesasService','notificaciones','cocinaService','cajaService','paraLlevarService', function($rootScope, $scope, sesionesControl, alerts,navegacion,loginService,messagesService,mesasService,notificaciones,cocinaService,cajaService,paraLlevarService) {
	$rootScope.alerts = {
		login : {
			message : "",
			type : ""
		},
		account : {
			message : "",
			type : ""
		},
		recover : {
			message : "",
			type : ""
		}
	}
	//$rootScope.mesaArray=[];
	$rootScope.entregar = sesionesControl.getList("entregar");
	$rootScope.cocinaAlert = sesionesControl.getList("cocinaAlert");
	
	$rootScope.d="";
	
	var usuarioInfo = sesionesControl.getList("usuario");
	if (usuarioInfo != null) {
		//$rootScope.login.nombre = usuarioInfo.nombre;
		$rootScope.chat = messagesService('/rest');
		$rootScope.menu = usuarioInfo.permisos
	}
	$scope.inf = "";
/*	if (usuario != null) {
		console.log("entrando")
		$scope.menu = sesionesControl.getList().permisos
	}*/
	
	
	$scope.navegar = function(accion){
		if(accion != null){
			if(accion == "logout"){
				loginService.logout()
			}else{
				loginService.comprobar(accion).then(function(datos){
					if(datos){
						navegacion.setLocation(accion);
					}else{
						navegacion.setLocation("error");
					}
				})
				
			}
		}
	}
	
	if($scope.chat != undefined){
	$scope.chat.connect("","",function(){
		$scope.chat.subscribe("/topic/obtenerMesasAgregadas",function(response){
			var body = JSON.parse(response.body)
			if(body.created){
				loginService.comprobar('caja').then(function(datos){
					if(!datos){
						loginService.comprobar('cocina').then(function(res){
							if(res){
								cocinaService.getTono().then(function(tono){
									var audio = new Audio('/sounds/'+tono.nombre+'.mp3');
							        audio.play();
								})								
							}
						})
					}
				})
				notificaciones.setCocina();
				$rootScope.ordenes=cocinaService.obtener_ordenes();
			}
			if(body.entregar){
				loginService.comprobar('caja').then(function(datos){
					if(!datos){
						loginService.comprobar('mesas').then(function(res){
							if(res){
								cocinaService.getTono().then(function(tono){
									var audio = new Audio('/sounds/'+tono.nombre+'.mp3');
							        audio.play();
								})
							}
						})
					}
				})
				 
				notificaciones.setEntregar();
			}
			mesasService.recuperar_mesas().then(function(datos){
				$rootScope.mesaArray = datos;
			});
			
		})
		
		/*$scope.chat.subscribe("/topic/obtenerMesasParaLlevar",function(response){
			var body = JSON.parse(response.body)
			if(body.created){
				notificaciones.setCocina();
				$rootScope.ordenes=cocinaService.obtener_ordenes();
			}
			if(body.entregar){
				notificaciones.setEntregar();
			}
			paraLlevarService.recuperar_mesas().then(function(datos){
				$rootScope.paraLlevarArray = datos;
			});
			
		})
		*/
		$scope.chat.subscribe("/topic/unsetEntregar",function(reponse){
			cajaService.obtenerCaja().then(function(datos){
				for(p=0;p<datos.length;p++){
					datos[p].total = Number(datos[p].total).toFixed(2)
				}
				$rootScope.pendientesCobro = datos;
			});
			notificaciones.unsetEntregar();
		})
		$scope.chat.subscribe("/topic/unsetCocina",function(response){
			notificaciones.unsetCocina();
		})
	})
	}
	
	$scope.volver = function() {
		window.history.back();
	}
} ]);