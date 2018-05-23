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

function validateUpdatebtnRoutes_Management() {

	

	

	var nm = $('#tboxRtName').val();
	var pc = $('#tboxRtPrice').val();
	var cl = $('#tboxRtColor').val();

	var Nm_R = nm.replace(/(^\s+|\s+$)/g,'');
	var Pc_R = pc.replace(/(^\s+|\s+$)/g,'');
	var Cl_R = cl.replace(/(^\s+|\s+$)/g,'');

	return true;
	/*

	if(Nm_R == "" || Pc_R == "" || Cl_R = ""){

		alert("Ningun Capo puede ir en Blanco");
		return false;
	}
	return true;
	*/
}