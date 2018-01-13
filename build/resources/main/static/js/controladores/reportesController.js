app.controller('ReportesController', [ '$scope', '$resource', 'reportesService','loginService','navegacion', function($scope, $resource, reportesService,loginService,navegacion) {
	
	loginService.comprobar('reportes').then(function(datos){
		if(!datos){
			navegacion.setLocation("error");
		}
	})
	
	$scope.totalVentas = 0;
	$scope.totalGastos = 0;
	
	$scope.reporte={};
	hoy = new Date();
	manana = new Date();
	$scope.r="rotacion";
	$scope.s="";
	hoy.setHours(0);
	manana.setHours(23);
	hoy.setMinutes(0);
	manana.setMinutes(59);
	hoy.setSeconds(0);
	manana.setSeconds(59);
	hoy.setMilliseconds(0);
	manana.setMilliseconds(0);
	$scope.reporte.fecha_hoy = hoy;
	$scope.reporte.fecha_manana = manana;
	
	function obtenerReporteVentas(){
		reportesService.getReporteVentas($scope.reporte).then(function(datos){
			for(i=0;i<datos.length;i++){
				datos[i].total = Number(datos[i].total).toFixed(2);
				datos[i].subtotal = Number(datos[i].subtotal).toFixed(2);
				datos[i].detalle_orden = reportesService.getDO(datos[i]);
				for(j = 0; j < datos[i].detalle_orden.length; j++){
					
					datos[i].detalle_orden[j].precio = Number(datos[i].detalle_orden[j].precio).toFixed(2);
					console.log(datos[i].detalle_orden[j])
				}
			}			
			$scope.ventasArray = datos;
			actualizarTotal();
		})
	}
	
	obtenerReporteVentas();
	
	reportesService.getCategorias().then(function(datos){
		datos.unshift({id:'%%',nombre:'Todos'});
		$scope.reporte.categorias = datos;
		$scope.reporte.categoria = $scope.reporte.categorias[0].id;
		$scope.masVendidosArray = reportesService.getMasVendidos($scope.reporte);
	})
	
	
	
	reportesService.getTiposVentas().then(function(datos){
		 datos.unshift({id:'0',nombre:'Todos'});
		 $scope.reporte.ventas = datos;
		 $scope.reporte.tipo_venta = $scope.reporte.ventas[0].id;
	})
	
	
	
	$scope.cambiarReporte=function(){
		if($scope.reporte.tipo_reporte == 1){
			$scope.r = "rotacion";
			$scope.s = "";
		}
		else{
			$scope.r = "";
			$scope.s = "rotacion";
		}
	}

	function obtenerReporteGastos(){
		reportesService.getReporteGastos($scope.reporte).then(function(datos){
			$scope.gastosArray = datos;
			actualizarTotal();
		})
	}
	
	obtenerReporteGastos();
	
	function actualizarTotal(){
		$scope.totalVentas = 0;
		$scope.totalGastos = 0;
		
		for(i = 0; i < $scope.ventasArray.length; i++){
			$scope.totalVentas = Number(Number($scope.ventasArray[i].total) + Number($scope.totalVentas)).toFixed(2);
		}
				
		for(j = 0; j < $scope.gastosArray.length; j++){
			$scope.totalGastos = Number(Number($scope.gastosArray[j].total) + Number($scope.totalGastos)).toFixed(2);
		}
		
		$scope.totalUtilidad = Number($scope.totalGastos - $scope.totalVentas).toFixed(2); 
		
	}	
	
	reportesService.getTiposGastos().then(function(datos){
		for(i=0;i<datos.length;i++){
			datos[i].total = Number(datos[i].total).toFixed(2);
		}
		datos.unshift({id:'0',nombre:'Todos'});
		$scope.reporte.gastos = datos;
		$scope.reporte.tipo_gasto = $scope.reporte.gastos[0].nombre;
	})
	
	$scope.reporte.tipos = [
		{
			id : "1",
			name : "Ventas"
		},
		{
			id : "2",
			name : "Gastos"
		}
	]
	
	$scope.reporte.tipo_reporte = $scope.reporte.tipos[0].id;
	
	$scope.actualizar = function(){
		console.log("entrando")
		reportesService.getReporteVentas($scope.reporte).then(function(datos){
			console.log(datos)
			for(i=0;i<datos.length;i++){
				datos[i].total = Number(datos[i].total).toFixed(2);
			}
			$scope.ventasArray = datos;
			actualizarTotal();
		});
		reportesService.getReporteGastos($scope.reporte).then(function(datos){
			for(i=0;i<datos.length;i++){
				datos[i].total = Number(datos[i].total).toFixed(2);
			}
			$scope.gastosArray = datos;
			actualizarTotal();
		});
		
		$scope.masVendidosArray = reportesService.getMasVendidos($scope.reporte);
	}
	
	$scope.cambiarTipoVenta=function(){
		if($scope.reporte.tipo_venta == '0'){
			obtenerReporteVentas();
		}else{
			console.log("entrando")
			reportesService.getReporteVentasFiltro($scope.reporte).then(function(datos){
			for(i=0;i<datos.length;i++){
				datos[i].total = Number(datos[i].total).toFixed(2);
			}
			$scope.ventasArray = datos;
			actualizarTotal();
		})
		}
	}
	
	$scope.cambiarTipoGasto=function(){
		if($scope.reporte.tipo_gasto == 'Todos'){
			obtenerReporteGastos();
		}else{
			reportesService.getReporteGastosFiltro($scope.reporte).then(function(datos){
				for(i=0;i<datos.length;i++){
					datos[i].total = Number(datos[i].total).toFixed(2);
				}
				$scope.gastosArray = datos;
				actualizarTotal();
			})
		}
	}
	
	$scope.filtroCategoria=function(){
		$scope.masVendidosArray = reportesService.getMasVendidos($scope.reporte);
	}
	
	$scope.imprimirMini=function(){
		reportesService.getReporteVentas($scope.reporte).then(function(datos){
			reportesService.getReporteGastos($scope.reporte).then(function(gastos){
			reportesService.getEmpresa().then(function(empresa){
				
				Ventas = 0;
				Gastos = 0;
				
				for(i = 0; i < datos.length; i++){
					Ventas = Number(Number(datos[i].total) + Number(Ventas)).toFixed(2);
				}
						
				for(j = 0; j < gastos.length; j++){
					Gastos = Number(Number(gastos[j].total) + Number(Gastos)).toFixed(2);
				}
				
				Utilidad = Number(Gastos - Ventas).toFixed(2); 
				
				reporte = [];
				efectivo = {nombre:"Ventas en Efectivo",importe:0};
				tarjeta = {nombre:"Ventas con Tarjeta",importe:0};
				cheque = {nombre:"Ventas con Cheque",importe:0};
				transferencia = {nombre:"Ventas con Transferencia",importe:0};
				otros = {nombre:"Ventas Otros",importe:0};
				ventas = {nombre:"Total de ventas",importe:Ventas};
				gastos = {nombre:"Total Gastos",importe:Gastos};
				total = {nombre:"Total final",importe:Utilidad};
				
				for(i = 0; i < datos.length; i++){
					if(datos[i].tipoPago.nombre == "Efectivo"){
						efectivo.importe += datos[i].total;
					}
					if(datos[i].tipoPago.nombre == "Tarjeta"){
						tarjeta.importe += datos[i].total;
					}
					if(datos[i].tipoPago.nombre == "Cheque"){
						cheque.importe += datos[i].total;
					}
					if(datos[i].tipoPago.nombre == "Transferencia"){
						transferencia.importe += datos[i].total;
					}
					if(datos[i].tipoPago.nombre == "Otros"){
						otros.importe += datos[i].total;
					}
				}
				reporte.push(efectivo);
				reporte.push(tarjeta);
				reporte.push(cheque);
				reporte.push(transferencia);
				reporte.push(otros);
				reporte.push(ventas);
				reporte.push(gastos);
				reporte.push(total);
				generarMini(reporte,empresa);
			})
		})
	})
	}
	
	$scope.imprimir=function(){
		reporte = document.getElementById('print');
		var ventimp=window.open(' ','popimpr');
		ventimp.document.write(reporte.innerHTML);
		ventimp.document.close();
		var css = ventimp.document.createElement("link");
		css.setAttribute("href", "../css/material.min.css");
		css.setAttribute("rel", "stylesheet");
		css.setAttribute("type", "text/css");
		ventimp.document.head.appendChild(css);
		ventimp.print();
		ventimp.close();
	}
	
	function imprimirPlatillos(){
		reporte = document.getElementById('print-platillos');
		var ventimp=window.open(' ','popimpr');
		ventimp.document.write(reporte.innerHTML);
		ventimp.document.close();
		var css = ventimp.document.createElement("link");
		css.setAttribute("href", "../css/material.min.css");
		css.setAttribute("rel", "stylesheet");
		css.setAttribute("type", "text/css");
		ventimp.document.head.appendChild(css);
		ventimp.print();
		ventimp.close();
	}
	
	$scope.imprimirPlatillos=function(){
		imprimirPlatillos();
	}
	
	$scope.imprimirPlatillosMini=function(){
		imprimirPlatillos();
	}	
}])