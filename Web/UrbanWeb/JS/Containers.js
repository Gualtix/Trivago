class Stop{

	constructor(codigo,nombre,latitud,longitud){
		this.codigo = codigo;
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
	}
}

class Path{

	constructor(origen,destino,trafico){
		this.origen = origen;
		this.destino = destino;
		this.Distance_Value = this.getDistanceFromLatLonInKm(origen.latitud,origen.longitud,destino.latitud,destino.longitud);
		this.trafico = trafico;
	}

	getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
		var R = 6371; 
		var dLat = this.deg2rad(lat2-lat1);
		var dLon = this.deg2rad(lon2-lon1); 
		var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			Math.cos(this.deg2rad(lat1)) * Math.cos(this.deg2rad(lat2)) * 
			Math.sin(dLon/2) * Math.sin(dLon/2); 
		var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		var d = R * c;
		return d;
	}

	deg2rad(deg) {
		return deg * (Math.PI/180);
	}
}

class Route{
	constructor(codigo,nombre,color,precio,PathList){
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.color = color;
		this.PathList = PathList;
	}
}