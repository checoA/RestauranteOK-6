app.controller('CajaController', [ '$scope', '$resource', 'cajaService','loginService','navegacion','$rootScope','barraParaLlevarService', function($scope, $resource, cajaService,loginService,navegacion,$rootScope,barraParaLlevarService) {
	
	loginService.comprobar('caja').then(function(datos){
		if(!datos){
			navegacion.setLocation("error");
		}
	})
	
	cajaService.obtenerCaja().then(function(datos){
		for(i=0;i<datos.length;i++){
			datos[i].total = Number(datos[i].total).toFixed(2)
		}
		console.log(datos)
		$rootScope.pendientesCobro = datos;
	});
	$scope.total_venta = 0;
	$scope.numero_mesa = 0;
	$scope.cambio = Number(0).toFixed(2);
	$scope.aumento = {};
	$scope.detalle = [];
	$scope.aumento.valor = Number(0).toFixed(2);
	$scope.orden = {};
	var dialog = document.querySelector('#modal-pago');
    var showDialogButton = document.querySelector('#btn-rv');
    if (! dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
      }
	
    var detalle = document.getElementById('modal-detalle');
    var showDialogButton = document.querySelector('#btn-dv');
    if (!detalle.showModal) {
        dialogPolyfill.registerDialog(dialog);
      }
    
	$scope.tipos_pago_array = cajaService.obtenerTiposPago();
	$scope.tipos_pago_array.$promise.then(function(datos){
		$scope.caja={metodo_pago:datos[0]};
	});
	
	$scope.calcularCambio=function(){
		calcularCam();
	}
	
	$scope.detalleVenta=function(orden){
		$scope.numero_mesa = orden.numMesa;
		cajaService.obtenerDetalle(orden).then(function(datos){
			for(i = 0;i < datos.length;i++){
				datos[i].precio = Number(datos[i].precio).toFixed(2);
			}
			$scope.detallesArray = datos;
		})		
		detalle.showModal();
	}
	
	function calcularCam(){
		c = $scope.caja.pago - $scope.orden.total;
		if(c>0){
			$scope.cambio = Number(c).toFixed(2);
		}
		else{
			$scope.cambio = Number(0).toFixed(2);
		}
	}
	
	$scope.calcularAumento=function(){
		calcularAum();
	}
	
	function calcularAum(){
		cajaService.obtenerAumentos($scope.caja.metodo_pago.nombre).then(function(aumento){
			$scope.aumento = aumento;
			$scope.orden.total = Number(Number($scope.total_venta) + Number(Number($scope.total_venta) * Number(aumento.valor)) / 100).toFixed(2);
			$scope.aumento.valor = Number(Number(Number($scope.total_venta) * Number(aumento.valor)) / 100).toFixed(2);
			calcularCam();
		})
	}
	
	$scope.ticket=function(){
		if($scope.caja.pago >= $scope.orden.total && $scope.caja.pago != ""){
			cajaService.obtenerProductos($scope.orden.id).then(function(datos){
				cajaService.getEmpresa().then(function(empresa){
					generarTicket($scope.orden.id,datos,$scope.orden.total,$scope.caja.pago,$scope.cambio,null,$scope.caja.metodo_pago,empresa,$scope.total_venta,$scope.aumento.valor)
					$scope.orden.subtotal = $scope.total_venta;
					$scope.orden.aumento = $scope.aumento.valor/$scope.total_venta*100;
					$scope.orden.tipoPago = $scope.caja.metodo_pago;
					$scope.orden[0].orden.pago = $scope.caja.pago;
					cajaService.concretarVenta($scope.orden).then(function(datos){
						dialog.close();
						cajaService.obtenerCaja().then(function(datos){
							$rootScope.pendientesCobro = datos;
						});
					});		
				})
			})
		}else{
			$scope.c = "error";
		}	
		//recargar pagina
	}
	
	$scope.imprimir=function(orden){
		$scope.orden = orden;
		//////recuperar orden de bd
		barraParaLlevarService.obtenerOrden($scope.orden.id).then(function(orden){
			$scope.orden = orden;
			$scope.orden.aumento = Number(Number(Number($scope.orden.subtotal) * Number($scope.orden.aumento)) / 100).toFixed(2);			
			cajaService.obtenerProductos($scope.orden.id).then(function(datos){
				cajaService.getEmpresa().then(function(empresa){
					c = $scope.orden.pago - $scope.orden.total;
					console.log($scope.orden)
					generarTicket($scope.orden.id,datos,$scope.orden.total,$scope.orden.pago,c,null,$scope.orden.tipoPago.nombre,empresa,$scope.orden.subtotal,$scope.orden.aumento)
					});		
				})
		})		
	}
	
	$scope.realizarVenta = function(elemento){
		$scope.numero_mesa = elemento.numMesa;
		$scope.total_venta = elemento.total;
		$scope.orden = angular.copy(elemento);
		calcularAum();
		dialog.showModal();
	}
	
	$scope.liberar = function(item){
		$scope.orden = item
		cajaService.liberar(item);
		cajaService.obtenerCaja().then(function(datos){
			$rootScope.pendientesCobro = datos;
		});
	}
	
	$scope.cerrar = function(){
		detalle.close();
	}
	
	dialog.querySelector('.close').addEventListener('click',function(){
		dialog.close();
		$scope.c = "";
		$scope.caja={metodo_pago:$scope.caja.metodo_pago};
		$scope.aumento = {};
		$scope.aumento.valor = Number(0).toFixed(2);		
	})
	
}])