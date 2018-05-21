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
		this.Distance_Value = calculateDistance(origen,destino);
		this.trafico = trafico;
	}

	calculateDistance(Begin_LatLong,End_LatLong){
		var lat1 = Begin_LatLong.lat();
		var lon1 = Begin_LatLong.lng();

		var lat2 = End_LatLong.lat();
		var lon2 = End_LatLong.lng();

		return getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2);
	}

	getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
		var R = 6371; 
		var dLat = deg2rad(lat2-lat1);
		var dLon = deg2rad(lon2-lon1); 
		var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
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
	constructor(codigo,nombre,color,precio,StopList){
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.color = color;
		this.StopList = StopList;
	}
}