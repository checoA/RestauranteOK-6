app.controller('BarraController', [ '$scope', '$resource', 'barraService','messagesService','loginService','navegacion','$rootScope','cajaService','$timeout', function($scope, $resource, barraService,messagesService,loginService,navegacion,$rootScope,cajaService,$timeout) {
	
	loginService.comprobar('barra').then(function(datos){
		if(!datos){
			navegacion.setLocation("error");
		}
	})
	
	
	loginService.comprobar('caja').then(function(datos){
		$scope.permisos = datos;
	})
	
	
	$scope.productosArray = barraService.obtener_productos();
	$scope.cantidad = '0';
	//$scope.num_mesa = barraService.getMesa();
	
	var dialog = document.querySelector('#modal-pago');
	var extra = document.querySelector('#modal-extra');
    var showDialogButton = document.querySelector('#btn-rv');
    if (! dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
      }
	if(!extra.showModal){
		dialogPolyfill.registerDialog(extra);
	}
    
    
	function recuperarOrden(){
		barraService.recuperarOrden().then(function(datos){
			 $scope.productosVenta = datos;
			 if(datos[0] != undefined){
				 $scope.numClientes = datos[0].orden.numPersonas;
			 }
			 else{
				 $scope.numClientes = 0;
			 }
			if($scope.productosVenta.length > 0){
				$scope.total = 0;
				for (var i = 0; i < $scope.productosVenta.length; i++) {
						$scope.productosVenta[i].precio = Number($scope.productosVenta[i].precio).toFixed(2);
						$scope.total = Number(Number($scope.total) + Number($scope.productosVenta[i].precio)).toFixed(2);
						$scope.productosVenta[i].producto.precio = Number($scope.productosVenta[i].precio).toFixed(2);
						ayudante_total = Math.round($scope.total * 100) / 100
					    var totalx = (ayudante_total - parseInt(ayudante_total)).toFixed(2)
					    if (totalx < .25) {
					        totalx = 0
					    } else if (totalx > .75) {
					        totalx = 1
					    } else {
					        totalx = .5
					    }
					    $scope.total = parseFloat(parseInt(ayudante_total) + totalx)
					    $scope.total = Number($scope.total).toFixed(2);
				}
			}
			else{
				$scope.total = 0;
			}

		});
	}
	
	function obtenerOrdenes(){
		recuperarOrden();
	}
	
	obtenerOrdenes();
		
	$scope.calcular=function(n){
		if(n=='c'){
			$scope.cantidad = $scope.cantidad.substring(0,$scope.cantidad.length - 1);
		}
		else if(n=='r'){
			$scope.cantidad = '0';
		}
		else{
			if($scope.cantidad[0] == '0'){
				$scope.cantidad = ""+n;
			}
			else{
		$scope.cantidad += ""+n;
			}
		}
		
	}
	
	$scope.agregarProductoExtra = function(){
		p = {};
		p.cantidad = 1;
		p.precio = Number($scope.extra.precio).toFixed(2);
		p.producto = $scope.extra;
		p.producto.precio = Number().toFixed(2);
		p.producto.cantidad = p.cantidad;
		p.estado = {nombre:"Barra",id:1};
		/*if(!ya_existe_en_orden(p)){*/
		$scope.productosVenta.push(p);
			$scope.total = Number(Number($scope.total) + Number(p.precio)).toFixed(2);
			ayudante_total = Math.round($scope.total * 100) / 100
		    var totalx = (ayudante_total - parseInt(ayudante_total)).toFixed(2)
		    if (totalx < .25) {
		        totalx = 0
		    } else if (totalx > .75) {
		        totalx = 1
		    } else {
		        totalx = .5
		    }
		    $scope.total = parseFloat(parseInt(ayudante_total) + totalx)
		    $scope.total = Number($scope.total).toFixed(2);
		/*}*/
		barraService.enviarVentas($scope.productosVenta,$scope.numClientes).then(function(datos){
			if(datos.created){
				recuperarOrden();
				//$rootScope.chat.send("/app/setCocina", {}, JSON.stringify(datos));
				//navegacion.setLocation("mesas");
			}
		})
	}
	
	$scope.agregarProducto = function(id){
		if($scope.cantidad!= '0'){
			barraService.obtener_x_id(id).then(function(datos){
				p = {};
				p.cantidad = $scope.cantidad;
				p.precio = Number($scope.cantidad * datos.precio).toFixed(2);
				p.producto = datos; 
				p.producto.precio = Number(p.precio).toFixed(2);
				p.producto.cantidad = p.cantidad;
				p.estado = {nombre:"Barra",id:1};
				if(!ya_existe_en_orden(p)){
					$scope.productosVenta.push(p);
					$scope.total = Number(Number($scope.total) + Number(p.precio)).toFixed(2);
					ayudante_total = Math.round($scope.total * 100) / 100
				    var totalx = (ayudante_total - parseInt(ayudante_total)).toFixed(2)
				    if (totalx < .25) {
				        totalx = 0
				    } else if (totalx > .75) {
				        totalx = 1
				    } else {
				        totalx = .5
				    }
				    $scope.total = parseFloat(parseInt(ayudante_total) + totalx)
				    $scope.total = Number($scope.total).toFixed(2);
				}
				$scope.cantidad='0';
				barraService.enviarVentas($scope.productosVenta,$scope.numClientes).then(function(datos){
					$scope.productosVenta =  [];
					recuperarOrden();
					$timeout(function(){
						
					if(datos.created){
						/*var bandera=false;
						
						for(i = 0; i<$scope.productosVenta.length;i++){
							console.log($scope.productosVenta[i].producto.categoria.nombre)
							if($scope.productosVenta[i].producto.categoria.nombre =="Carnes"){
								bandera = true;
							}
						}
						console.log(bandera)
						if(bandera == true){*/
							$rootScope.chat.send("/app/setCocina", {}, JSON.stringify(datos));
						//}
					}
					},1000)
				})
			})			
		}
	}
	
	$scope.agregarClientes=function(){
		barraService.enviarVentas($scope.productosVenta,$scope.numClientes).then(function(datos){
			$scope.productosVenta =  [];
			recuperarOrden();
	})
	}
	
	$scope.eliminar_producto=function(elemento){
		
		barraService.eliminarProducto(elemento).then(function(datos){
			//obtenerOrdenes();
			i = obtenerIndice(elemento);
			console.log($scope.productosVenta)
			$scope.productosVenta.splice(i,1);
			console.log($scope.productosVenta)
			$scope.productosVenta = $scope.productosVenta;
			if($scope.productosVenta.length > 0){
				$scope.total = 0;
				for (var i = 0; i < $scope.productosVenta.length; i++) {
						$scope.productosVenta[i].producto.precio = Number($scope.productosVenta[i].precio).toFixed(2);	
						$scope.productosVenta[i].precio = Number($scope.productosVenta[i].precio).toFixed(2);
						$scope.total = Number(Number($scope.total) + Number($scope.productosVenta[i].precio)).toFixed(2);
						ayudante_total = Math.round($scope.total * 100) / 100
					    var totalx = (ayudante_total - parseInt(ayudante_total)).toFixed(2)
					    if (totalx < .25) {
					        totalx = 0
					    } else if (totalx > .75) {
					        totalx = 1
					    } else {
					        totalx = .5
					    }
					    $scope.total = parseFloat(parseInt(ayudante_total) + totalx)
					    $scope.total = Number($scope.total).toFixed(2);
				}
			}
		})		
	}
	
	$scope.enviarVenta=function(){
		if($scope.productosVenta.length > 0){
			barraService.enviarVentas($scope.productosVenta,$scope.numClientes).then(function(datos){
				console.log("aqui si entro")
				if(datos.created){
					console.log("entrando")
					//recuperarOrden();
					$rootScope.chat.send("/app/setCocina", {}, JSON.stringify(datos));
					navegacion.setLocation("mesas");
				}
			})
		}
	}
	
	$scope.cancelarVenta=function(){		
		barraService.cancelarVenta($scope.productosVenta);
		$scope.productosVenta = []
		$scope.total = 0;
	}
	
	$scope.imprimir = function(){
		reporte = document.getElementById('cont-tabla');
		var ventimp=window.open(' ','popimpr');
		ventimp.document.write(reporte.innerHTML);
		ventimp.document.close();
		var css = ventimp.document.createElement("link");
		css.setAttribute("href", "../css/material.min.css");
		css.setAttribute("rel", "stylesheet");
		css.setAttribute("type", "text/css");
		ventimp.document.head.appendChild(css);
		
		css.setAttribute("href", "../css/barra.css");
		css.setAttribute("rel", "stylesheet");
		css.setAttribute("type", "text/css");
		ventimp.document.head.appendChild(css);
		
		ventimp.print();
		ventimp.close();
	}
	
	function obtenerIndice(elemento){
		respuesta = 0;
		for (var i = 0; i < $scope.productosVenta.length; i++) {
			if(elemento.producto.id == $scope.productosVenta[''+i].producto.id && elemento.$$hashKey == $scope.productosVenta[''+i].$$hashKey){				
				respuesta = i;
			}
		}
		return respuesta;
	}
	
	function ya_existe_en_orden(orden){
		respuesta = false;
		for (var i = 0; i < $scope.productosVenta.length; i++) {
			if(orden.producto.id == $scope.productosVenta[''+i].producto.id && $scope.productosVenta[''+i].estado.id != 6 && $scope.productosVenta[""+i].estado.id !=5 && $scope.productosVenta[""+i].estado.id !=4 && $scope.productosVenta[""+i].producto.visto == false){
				$scope.productosVenta[''+i].cantidad = Number(Number($scope.productosVenta[''+i].cantidad) + Number(orden.cantidad));
				$scope.productosVenta[''+i].precio = Number(Number($scope.productosVenta[''+i].precio) + Number(orden.precio)).toFixed(2);
				$scope.total = Number(Number($scope.total) + Number(orden.precio)).toFixed(2);
				 ayudante_total = Math.round($scope.total * 100) / 100
				    var totalx = (ayudante_total - parseInt(ayudante_total)).toFixed(2)
				    if (totalx < .25) {
				        totalx = 0
				    } else if (totalx > .75) {
				        totalx = 1
				    } else {
				        totalx = .5
				    }
				    $scope.total = parseFloat(parseInt(ayudante_total) + totalx)
				    $scope.total = Number($scope.total).toFixed(2);
				    
				respuesta = true;
			}
		}
		return respuesta;
	}
	
	$scope.imprimir=function(){
		$scope.orden = angular.copy($scope.productosVenta);
		//////recuperar orden de bd
		barraService.obtenerOrden($scope.orden[0].orden.id).then(function(orden){
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
	
	$scope.ticket=function(){
		if($scope.caja.pago >= $scope.orden.total && $scope.caja.pago != ""){
			cajaService.obtenerProductos($scope.orden[0].orden.id).then(function(datos){
				cajaService.getEmpresa().then(function(empresa){
					generarTicket($scope.orden[0].orden.id,datos,$scope.orden.total,$scope.caja.pago,$scope.cambio,null,$scope.caja.metodo_pago,empresa,$scope.total_venta,$scope.aumento.valor)
					$scope.orden[0].orden.total = $scope.orden.total;
					$scope.orden[0].orden.subtotal = $scope.total_venta;
					$scope.orden[0].orden.aumento = $scope.aumento.valor/$scope.total_venta*100;
					$scope.orden[0].orden.tipoPago = $scope.caja.metodo_pago;
					$scope.orden[0].orden.pago = $scope.caja.pago;
					$scope.orden[0].orden.numPersonas = $scope.numClientes;
					cajaService.concretarVentaParaLlevar($scope.orden[0].orden).then(function(datos){
						dialog.close();
					});		
				})
			})
		}else{
			$scope.c = "error";
		}	
	}
	
	$scope.imprimirPreviaVenta = function(){
		$scope.orden = angular.copy($scope.productosVenta);
		//////recuperar orden de bd
		barraService.obtenerOrden($scope.orden[0].orden.id).then(function(orden){
			$scope.orden = orden;
			cajaService.obtenerProductos($scope.orden.id).then(function(datos){
				cajaService.getEmpresa().then(function(empresa){
					generarTicketPrevio($scope.orden.id,datos,$scope.total,null,empresa)
					});		
				})
		})
	}
	
	$scope.calcularCambio=function(){
		calcularCam();
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
	
	function calcularAum(){
		cajaService.obtenerAumentos($scope.caja.metodo_pago.nombre).then(function(aumento){
			$scope.aumento = aumento;
			$scope.orden.total = Number(Number($scope.total_venta) + Number(Number($scope.total_venta) * Number(aumento.valor)) / 100).toFixed(2);
			 ayudante_total = Math.round($scope.orden.total * 100) / 100
			    var totalx = (ayudante_total - parseInt(ayudante_total)).toFixed(2)
			    if (totalx < .25) {
			        totalx = 0
			    } else if (totalx > .75) {
			        totalx = 1
			    } else {
			        totalx = .5
			    }
			    $scope.orden.total = parseFloat(parseInt(ayudante_total) + totalx)
			    $scope.orden.total = Number($scope.orden.total).toFixed(2);
			$scope.aumento.valor = Number(Number(Number($scope.total_venta) * Number(aumento.valor)) / 100).toFixed(2);
			calcularCam();
		})
	}
	
	$scope.tipos_pago_array = cajaService.obtenerTiposPago();
	$scope.tipos_pago_array.$promise.then(function(datos){
		$scope.caja={metodo_pago:datos[0]};
	});
	
	
	$scope.calcularAumento=function(){
		calcularAum();
	}
	
	$scope.venta=function(){
		$scope.numero_mesa = barraService.getMesa();
		$scope.total_venta = $scope.total;
		$scope.orden = angular.copy($scope.productosVenta);
		calcularAum();
		dialog.showModal();
	}
	
	dialog.querySelector('.close').addEventListener('click',function(){
		dialog.close();
		$scope.c = "";
		$scope.caja={metodo_pago:$scope.caja.metodo_pago};
		$scope.aumento = {};
		$scope.aumento.valor = Number(0).toFixed(2);		
	})
	
	extra.querySelector('.close').addEventListener('click',function(){
		extra.close();
		$scope.extra={};
	})
	
	$scope.liberar = function(){
		$scope.orden = angular.copy($scope.productosVenta);
		barraService.liberar($scope.orden[0].orden);
		navegacion.setLocation("mesas");
	}
	
	$scope.productoExtra = function(){
		extra.showModal();
	}
	
}]);