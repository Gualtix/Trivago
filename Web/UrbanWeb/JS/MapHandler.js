//(^< ............ ............ ............ ............ ............ G L O V A L S
'use strict';
var markers = [];
var StopList = [];

var map;
var LatLong;
var OldStop;
var NewStop;

var wantNewStop = false;
var wantNewPath = false;

var flightPath;

var afterADD = false;

var Route_Test = [];

//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ initMap
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

function initMap() {

	LatLong = null;
	OldStop = null;
	NewStop = null;

	map = new google.maps.Map(document.getElementById('map'),
	{
		zoom: 15,
		center: {lat: 14.636717, lng: -90.519040},
		mapTypeId: 'roadmap'
	});

	map.addListener('click',function(e) 
	{

		LatLong = e.latLng;

		if(wantNewStop){

			if(OldStop == null){
				OldStop = LatLong;
				PlaceMarker(LatLong);
			}
			else{
				//QuitMarker(OldStop);
				if(!afterADD){
					Quit_Last_Marker_Added();

				}
				NewStop = LatLong;
				PlaceMarker(NewStop);
				OldStop = NewStop;
				afterADD = false;
			}
		}

		if(wantNewPath){

			/*

			PlaceMarker(LatLong);

			if(OldStop == null){
				OldStop = LatLong;
			}
			else{
				NewStop = LatLong;
				var Latitude = NewStop.lat();
				var Longitude = NewStop.lng();
				LinkStops(Latitude,Longitude);

				OldStop = NewStop;
			}
			*/
		}
	});
}

//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ P O L Y L I N E   A N D   M A R K E R S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

function PlaceMarker(LatLong){

	var MyIcon = 
	{
    	url: 'IMG/Bus_Stop.png',
    	size: new google.maps.Size(32,38),
        scaledSize: new google.maps.Size(32, 38),
        labelOrigin: new google.maps.Point(10,-8)
	};

	var cnt = markers.length;
	markers[cnt] = new google.maps.Marker({

	    position: LatLong,
	    map: map,
	    icon: MyIcon,
	    draggable: false,
	    labelClass: "labels",
	    label: 
	    {
	        text: StopName,
        	color: "#053054",
	    	fontSize: "17px",
	    	fontWeight: "bold"
	    }
	});

	markers[cnt].addListener('click', function(event) {     
		var Ps = event.latLng;

		if(checkUniquePos(Ps.lat(),Ps.lng())){
			$('#Latit').val(Ps.lat());  
			$('#Longit').val(Ps.lng());    
			Route_Test.push(new Stop("Si","Si","Si",Ps.lat(),Ps.lng()));
			buildSubRoute();


		}
	});

	SetLatLong_to_TextBox(LatLong);
}

function buildSubRoute() {
	var Lm = Route_Test.length;
	if(Lm > 1){
		var Begin = Route_Test[Lm - 2];
		var End = Route_Test[Lm - 1];
		LinkStops(Begin.Latitude,Begin.Longitude,End.Latitude,End.Longitude);
	}
}

function checkUniquePos(Lat,Long) {
	var cnt = 0;
	var Lm = Route_Test.length;
	while(cnt < Lm){

		var Ar_Latit = Route_Test[cnt].Latitude;
		var Ar_Longit = Route_Test[cnt].Longitude;

		if(Ar_Latit == Lat && Ar_Longit == Long){

			return false;
		}

		cnt++;
	}

	return true;
}

function QuitMarker(LatLong) {
	try {

		var lat = LatLong.lat();
		var long = LatLong.lng();
		markers[new google.maps.LatLng(lat, long)].setMap(null);
	} 
	catch (e){

	}
}

function Quit_Last_Marker_Added() {
	try {

		var cnt = markers.length - 1;
		markers[cnt].setMap(null);
	} 
	catch (e){

	}
}


function LinkStops(Bgn_Lat,Bgn_Long,End_Lat,End_Long){

	var flightPlanCoordinates =
	[
		{lat: Bgn_Lat, lng: Bgn_Long},
		{lat: End_Lat, lng: End_Long}
	];


	flightPath = new google.maps.Polyline({
		path: flightPlanCoordinates,
		geodesic: true,

		strokeColor: '#09f429',
		strokeOpacity: 1.0,
		strokeWeight: 2,
		icons:
		[{
			icon: {path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW},
			offset: '100%',
			repeat: '35px'
		}]
	
	});

	flightPath.setMap(map);
	animateCircle(flightPath);
}


/*

function LinkStops(New_Lat,New_Long){

	//(^< ............ Old
	var Old_Lat = OldStop.lat();
	var Old_Long = OldStop.lng();
	

	var flightPlanCoordinates =
	[
		{lat: Old_Lat, lng: Old_Long},
		{lat: New_Lat, lng: New_Long}
	];


	flightPath = new google.maps.Polyline({
		path: flightPlanCoordinates,
		geodesic: true,

		strokeColor: '#09f429',
		strokeOpacity: 1.0,
		strokeWeight: 2,
		icons:
		[{
			icon: {path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW},
			offset: '100%',
			repeat: '35px'
		}]
	
	});

	flightPath.setMap(map);
	animateCircle(flightPath);
}

*/

function animateCircle(line) {
	var count = 0;
	window.setInterval(function() 
	{
		count = (count + 1) % 200;

		var icons = line.get('icons');
		icons[0].offset = (count / 2) + '%';
		line.set('icons', icons);

	},20);
}

//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ A C T I O N S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

function Show_StopList_on_Map() {
	var cnt = 0;
    var Lm = StopList.length;
    while(cnt < Lm){

    	var Lat = StopList[cnt].Latitude;
    	var Long = StopList[cnt].Longitude;
    	StopName = StopList[cnt].Name;
        var Lat_Lg = new google.maps.LatLng(Lat,Long);
        PlaceMarker(Lat_Lg);
        cnt++;
    }

    StopName = undefined;
}

//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ C L E A N
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

function CleanMap() {
	RemoveMarkers();
	RemovePolylines();
}

function RemoveMarkers(){
	var i;
    for(i = 0; i < markers.length; i++){
        markers[i].setMap(null);
    }
    markers = [];
}

function RemovePolylines() {

	if(typeof flightPath !== "undefined") {
		flightPath.setMap(null);
	}
}

