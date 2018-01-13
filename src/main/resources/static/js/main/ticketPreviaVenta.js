function obtenerInfoTicketPrevio() {
    return infoTicket;
}

var infoTicket = new Object();

function generarInfoTicketPrevio(numero_venta,productos, total, fecha_venta,empresa) {
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
	infoTicket.fecha_venta = fecha_venta;
}

function generarTicketPrevio(numero_venta, productos, total, fecha_venta,empresa) {
	
	generarInfoTicketPrevio(numero_venta, productos, total, fecha_venta,empresa);
    
    var url = "../vistas/ticketPreviaVenta.html";
    var w = window.open(url, "mywindow", "menubar=0,resizable=1,width=350,height=500, resizable=0");
    w.print();
    w.onfocus = function () { setTimeout(function () { w.close(); }, 1500); };
}
