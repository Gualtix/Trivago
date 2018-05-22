//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ V A L I D A T E
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
function validateAddbtnStations_Management() {
	//Get
	//var bla = $('#txt_name').val();

	//Set
	//$('#txt_name').val(bla);

	var Nm = $('#tboxName').val();
	var Lat = $('#tboxLatitude').val();
	var Long = $('#tboxLongitude').val();

	var newNm = Nm.replace(/(^\s+|\s+$)/g,'');

	if(newNm == ""){

		alert("Nombre NO pueden ir en Blanco");
		return false;
	}

	if(Lat == "" || Long == ""){

		alert("Longitud y Latitud NO pueden ir en Blanco");
		return false;
	}

	return true;
}