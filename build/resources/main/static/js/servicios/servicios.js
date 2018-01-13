app.factory('stompService',['$rootScope', function($rootScope) {
    	
    	var client = {};
    	
        var service = function(url){
        	
            var socket = new SockJS(url);
            client = Stomp.over(socket);
            
            return {
            	subscribe: subscribe,
            	send: send,
            	connect: connect,
            	disconnect: disconnect
            }
    	};
        
		return service;
		
		function subscribe(queue, callback) {
            client.subscribe(queue, function() {
            	var args = arguments;
            	$rootScope.$apply(function() {
                    callback(args[0]);
                })
            })
		}
		
		function send(queue, headers, data){
			client.send(queue, headers, data);
		}
 
		function connect(user, password, on_connect, on_error, vhost) {
            client.connect(user, password,
            function(frame) {
             $rootScope.$apply(function() {
                 on_connect.apply(client, frame);
             })
            },
            function(frame) {
                $rootScope.$apply(function() {
                    on_error.apply(client, frame);
                })
            }, vhost);
			
		}
 
		function disconnect(callback){
            client.disconnect(function() {
                $rootScope.$apply(function() {
                    callback.apply(args);
                })
            })
		}
    }]
  );

app.factory("sesionesControl", function(){
    return{
        get: function(key){
            return sessionStorage.getItem(key);
        },
        set: function(key,val){
            return sessionStorage.setItem(key,val);
        },        
        getList: function(key){
            return JSON.parse(sessionStorage.getItem(key));
        },
        setList: function(key,list){
            return sessionStorage.setItem(key, JSON.stringify(list));
        },        
        unset: function(key){
            return sessionStorage.removeItem(key);
        },
        clear: function(){
            return sessionStorage.clear();
        }
    }
});

app.factory("alterMenu",['$rootScope',function($rootScope){
	return {
		setMenu : function(data){
			$rootScope.menu = data;
		}
	}
}])

app.factory("navegacion",['$location','loginService',function($location,loginService){
	return {
		setLocation:function(url){
				$location.path('/'+url);
		}
	}
}])

app.factory("notificaciones",['$rootScope','sesionesControl',function($rootScope,sesionesControl){
	return{
		setEntregar:function(){
			$rootScope.entregar = true;
			sesionesControl.setList("entregar",true);
		},
		setCocina:function(){
			sesionesControl.setList("cocinaAlert",true);
			$rootScope.cocinaAlert = true;
		},
		unsetEntregar:function(){
			sesionesControl.setList("entregar",false);
			$rootScope.entregar = false;
		},
		unsetCocina:function(){
			sesionesControl.setList("cocinaAlert",false);
			$rootScope.cocinaAlert = false;
		}
	}
}])

app.factory("fechaReal",[function(){
	return{
		getFechaReal(fecha){
			fechaReal = '';
			fechaReal += fecha.getFullYear();
			fechaReal += '-'+(fecha.getMonth()+1);
			fechaReal += '-'+ fecha.getDate();
			hora = fecha.getHours() > 10 ? fecha.getHours() : '0'+fecha.getHours();
			fechaReal += 'T'+hora;
			minutos = fecha.getMinutes() > 10 ? fecha.getMinutes() : '0'+fecha.getMinutes();
			fechaReal += ':'+minutos;
			segundos = fecha.getSeconds() > 10 ? fecha.getSeconds() : '0'+fecha.getSeconds();
			fechaReal += ':'+segundos+'.000Z';
			return fechaReal;
		}
	}
}])

app.factory("orden_mesa",['$rootScope','sesionesControl',function($rootScope,sesionesControl){
	return{
		setNumeroMesa:function(id){
			//$rootScope.mesaOrden = id;
			sesionesControl.setList("mesaOrden",id);
		},
		getNumeroMesa:function(){
			//return $rootScope.mesaOrden;
			return sesionesControl.getList("mesaOrden");
		},
		setBandera:function(b){
			sesionesControl.setList("bandera",b);
			//$rootScope.bandera = b;
		},
		getBandera:function(){
			//return $rootScope.bandera;
			return sesionesControl.getList("bandera");
		},
		setEstado:function(e){
			sesionesControl.setList("estado",e);
			//$rootScope.estado = e;
		},
		getEstado:function(){
			//return $rootScope.estado;
			return sesionesControl.getList("estado");
		}
	}
}])

app.factory("alerts", ['$rootScope', function($rootScope){
    return {
        showLogin : function(mensaje, tipo){   
        	$rootScope.alerts.login={message:mensaje,type:tipo}
        },        
        clearLogin : function(index){
            $rootScope.alerts.login = {};
        },
        showAccount : function(mensaje, tipo){   
        	$rootScope.alerts.account={message:mensaje,type:tipo}
        },        
        clearAccount : function(index){
            $rootScope.alerts.account = {};
        },
        showRecover : function(mensaje, tipo){   
        	$rootScope.alerts.recover={message:mensaje,type:tipo}
        },        
        clearRecover : function(index){
            $rootScope.alerts.recover = {};
        }
    }
}]);

