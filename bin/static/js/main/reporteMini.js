function obtenerInfo() {
    return info;
}

var info = new Object();

function generarInfo(reporte,empresa) {
    info.nombre_empresa = empresa.nombre;
    info.telefono = empresa.telefono;
    info.rfc = empresa.rfc;
    info.direccion = empresa.direccion;
    info.colonia = empresa.colonia;
    info.codigo = empresa.codigo;
    info.ciudad = empresa.ciudad;
    info.reporte = reporte;
}

function generarMini(reporte,empresa) {
	console.log(reporte)
    generarInfo(reporte, empresa);
    
    var url = "../vistas/reporteMini.html";
    var w = window.open(url, "mywindow", "menubar=0,resizable=1,width=350,height=500, resizable=0");
    w.print();
    w.onfocus = function () { setTimeout(function () { w.close(); }, 1500); };
}
