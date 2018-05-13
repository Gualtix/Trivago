//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ V A L I D A T E
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
function validateAddNewStation() {
	//Get
	//var bla = $('#txt_name').val();

	//Set
	//$('#txt_name').val(bla);

	

	var Nm = $('#tboxName').val();
	var Ps = $('#tboxPrice').val();
	var Lat = $('#tboxLatitude').val();
	var Long = $('#tboxLongitude').val();

	var newNm = Nm.replace(/(^\s+|\s+$)/g,'');
	var newPs = Ps.replace(/(^\s+|\s+$)/g,'');



	if(newNm == "" || newPs == ""){

		alert("Nombre y Precio NO pueden ir en Blanco");
		return false;
	}

	if(Lat == "" || Long == ""){

		alert("Longitud y Latitud NO pueden ir en Blanco");
		return false;
	}

	return true;
}