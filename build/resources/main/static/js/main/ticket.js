function obtenerInfoTicket() {
    return infoTicket;
}

var infoTicket = new Object();

function generarInfoTicket(numero_venta,productos, total, pago, cambio, fecha_venta, metodo_pago,empresa,total_sd,aumento) {
	infoTicket.nombre_empresa = empresa.nombre;
	infoTicket.telefono = empresa.telefono;
	infoTicket.rfc = empresa.rfc;
	infoTicket.direccion = empresa.direccion;
	infoTicket.colonia = empresa.colonia;
	infoTicket.codigo = empresa.codigo;
	infoTicket.ciudad = empresa.ciudad;
	infoTicket.numero_venta = numero_venta;
	infoTicket.productos = productos;
	infoTicket.total = total;
	infoTicket.pago = pago;
	infoTicket.cambio = cambio;
	infoTicket.fecha_venta = fecha_venta;
	infoTicket.metodo_pago = metodo_pago;
	infoTicket.total_sd = total_sd;
	infoTicket.aumento = aumento;
}

function generarTicket(numero_venta, productos, total, pago, cambio, fecha_venta, metodo_pago,empresa,total_sd,aumento) {
	
	generarInfoTicket(numero_venta, productos, total, pago, cambio, fecha_venta, metodo_pago, empresa,total_sd,aumento);
    
    var url = "../vistas/ticket.html";
    var w = window.open(url, "mywindow", "menubar=0,resizable=1,width=350,height=500, resizable=0");
    w.print();
    w.onfocus = function () { setTimeout(function () { w.close(); }, 1500); };
}
