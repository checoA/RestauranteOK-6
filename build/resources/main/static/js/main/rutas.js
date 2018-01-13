app.config(['$locationProvider','$routeProvider', function ($locationProvider, $routeProvider) {
  $routeProvider
    .when("/",{templateUrl: "../vistas/login.html", controller: "controladorPrincipal"})
    .when("/login",{templateUrl: "../vistas/login.html", controller: "controladorPrincipal"})
    .when("/listado",{templateUrl: '../vistas/logeado.html',controller: 'controladorPrincipal'})
    .when("/mesas",{templateUrl:'../vistas/mesas.html',controller:'controladorPrincipal'})
    .when("/barra",{templateUrl:'../vistas/barra.html',controller:'controladorPrincipal'})
    .when("/cocina",{templateUrl:'../vistas/cocina.html',controller:'controladorPrincipal'})
    .when("/caja",{templateUrl:'../vistas/Caja.html',controller:'controladorPrincipal'})
    .when("/empresa",{templateUrl:"../vistas/empresa.html",controller:'controladorPrincipal'})
    .when("/inventario",{templateUrl:"../vistas/inventario.html",controller:'controladorPrincipal'})
    .when("/platillos",{templateUrl:"../vistas/productos.html",controller:'controladorPrincipal'})
    .when("/usuarios",{templateUrl:"../vistas/usuarios.html",controller:'controladorPrincipal'})
    .when("/reportes",{templateUrl:"../vistas/reportes.html",controller:'controladorPrincipal'})
    .when("/gastos",{templateUrl:"../vistas/gastos.html",controller:'controladorPrincipal'})
    .when("/messages",{templateUrl:"../vistas/messages.html",controller:'controladorPrincipal'})
    .when("/otros",{templateUrl:"../vistas/configuraciones.html",controller:'controladorPrincipal'})
    .when("/paraLlevar",{templateUrl:"../vistas/paraLlevar.html",controller:'controladorPrincipal'})
    .when("/barraParaLlevar",{templateUrl:"../vistas/barraParaLlevar.html",controller:'controladorPrincipal'})
    .when("/404", {templateUrl: "../vistas/404.html", controller: "controladorPrincipal"})    
    .otherwise({redirectTo: '404'});
}]);