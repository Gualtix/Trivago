class Stop{

	constructor(Code,Name,Price,Latitude,Longitude){
		this.Code = Code;
		this.Name = Name;
		this.Price = Price;
		this.Latitude = Latitude;
		this.Longitude = Longitude;
	}
}

class Path{

	constructor(Begin_Point,End_Point,Distance_Value,Traffic_Value,Color){
		this.Begin_Point = Begin_Point;
		this.End_Point = End_Point;
		this.Distance_Value = Distance_Value;
		this.Traffic_Value = Traffic_Value;
		this.Color = Color;
	}

	calculateDistance(Begin_LatLong,End_LatLong){

	}
}