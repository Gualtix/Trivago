//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ G L O B A L S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
'use strict';
var Selected_Route_ID;


//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ S E R V E R   R E Q U E S T S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

function LoadRoutes_from_Server(){

    const Dir = 'http://localhost:8080/UServer/api/urban/getroutes';

    $.ajax({ 
        type: 'GET', 
        url: Dir, 
        data: { get_param: 'value' }, 
        dataType: 'json',
        success: function (data) { 

            $.each(data, function(codigo,Rt) {

                var Rt_codigo = Rt.codigo;
                var Rt_nombre = Rt.nombre;
                var Rt_color = Rt.color;
                var Rt_precio = Rt.precio;
                var Rt_estaciones = Rt.estaciones;

                var Tmp_PathList = [];

                $.each(Rt_estaciones, function(codigo,Pt) {

                    var Pt_origen = Pt.origen;
                    var Pt_destino = Pt.destino;
                    var Pt_trafico = Pt.trafico;

                    var Org = StopList[Pt_origen];
                    var Des = StopList[Pt_destino];

                    LinkStops(Org.latitud,Org.longitud,Des.latitud,Des.longitud,Rt_color,false);

                    var MyPath = new Path(Org,Des,Pt_trafico);

                    var cnt = Tmp_PathList.length;
                    Tmp_PathList[cnt] = MyPath;

                });

                var MyRoute = new Route(Rt_codigo,Rt_nombre,Rt_color,Rt_precio,Tmp_PathList);

                var amt =  RouteList.length;
                RouteList[amt] = MyRoute;
            });

            fill_Routes_Selector();
        },
        error: function (jqXHR, status) {
        console.log(jqXHR);
        alert('fail' + status.code);
        }
    });
}

//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ E V E N T S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............


function onClick_newRoute(){

	var Btn_S = $('#btn_newRoute').get(0);
    disableBtn(Btn_S);

    var Etn_U = $('#btn_updateRoute').get(0);
    enableBtn(Etn_U);

    RemovePolylines();
    RemovePolylines_Special();

    wantNewStop = false;
	wantNewPath = true;

	

}


function onClick_updateRoute(){


	var Btn_S = $('#btn_newRoute').get(0);
    enableBtn(Btn_S);

    var Etn_U = $('#btn_updateRoute').get(0);
    disableBtn(Etn_U);


    if(validateUpdatebtnRoutes_Management()){

    	var nm = $('#tboxRtName').val();
    	var pc = $('#tboxRtPrice').val();
    	//var tr = $('#tboxRtTraffic').val();
    	var cl = $('#tboxRtColor').val();


    	var NewInfo =
    	{
    		codigo:Selected_Route_ID,
    		nombre:nm,
    		precio:pc,
    		color:cl
    	}

        //(^< ............ To Server
        var data = NewInfo;
        $.ajax(
        {
            type: "POST",
            url: "http://localhost:8080/UServer/api/urban/update_route",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (data, status, jqXHR) 
            {
                //alert(success);
                alert("Ruta Actualizada Exitosamente");
            },

            error: function (jqXHR, status)
            {
                alert("Error: Ruta NO Actualizada");
            }
        });
    }

    CleanRouteSelector();
    fill_Routes_Selector();
    CleanRouteTable();
    
    //Update();
    //CleanMap();
    //Show_StopList_on_Map();
    //CleanStopForm();

}

function onchangeGetRouteID(selected_Rt) {

	var ID = selected_Rt.value;  
	Selected_Route_ID = ID;

	FillRouteInfo(ID)

	CleanRouteTable();
	UpdateRouteTable(ID);

	var Btn_U = $('#btn_newRoute').get(0);
    disableBtn(Btn_U);

    var Stn_U = $('#btn_updateRoute').get(0);
    enableBtn(Stn_U);

}


//(^< ............ ............ ............ ............ ............ onClickbtnbtnRoutes_Management
function Built_New_Route() {

    wantNewStop = false;
    wantNewPath = true;

    var Btn_St = document.getElementById("btnStations_Management");
    var Btn_Pt = document.getElementById("btnRoutes_Management");
    disableBtn(Btn_Pt);
    enableBtn(Btn_St);
    CleanMap();
    document.getElementById('gboxEstaciones').style.display = 'none';
    document.getElementById('gboxRutas').style.removeProperty('display');
    Show_StopList_on_Map();
    LoadRoutes_from_Server();
}


//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ A C T I O N S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............


function fill_Routes_Selector() {
	var $dropdown = $("#comboxSelectRoute");
	$.each(RouteList, function(Dts,Inf) {
		$dropdown.append($("<option />").val(Inf.codigo).text(Inf.nombre));
	});

}

function FillRouteInfo(Route_ID) {
	//Selected Route
	var aux = RouteList.length - Route_ID - 1;

	var SRT =  RouteList[aux];

	$('#tboxRtName').val(SRT.nombre);
    $('#tboxRtPrice').val(SRT.precio);
    $('#tboxRtTraffic').val(SRT.trafico);
    $('#tboxRtColor').val(SRT.color);
}

function UpdateRouteTable(Route_ID) {

	//Selected Route
	var aux = RouteList.length - Route_ID - 1;
	var SRT =  RouteList[aux];

	//List of Paths
	var Pt_Ls = SRT.PathList;

    CleanStopTable();
    var cnt = 0;
    var Lm = Pt_Ls.length;
    while(cnt < Lm){
        VisitRows_to_Update_Rt(Pt_Ls[cnt]);
        cnt++;
    }
}

function VisitRows_to_Update_Rt(Rt_S) {

    var table = document.getElementById('tblMyRoutes');
    var row = table.insertRow(1);

    var Cell_0 = row.insertCell(0);
    var Cell_1 = row.insertCell(1);
    var Cell_2 = row.insertCell(2);
    var Cell_3 = row.insertCell(3);

    var Dist = Rt_S.Distance_Value.toString();

    Dist = Dist.substring(0,4);

    Cell_0.innerHTML = Rt_S.origen.nombre;
    Cell_1.innerHTML = Rt_S.destino.nombre;
    Cell_2.innerHTML = Dist + " Km";
    Cell_3.innerHTML = Rt_S.trafico;

}

//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ C L E A N
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

function CleanRouteSelector() {
	$('#comboxSelectRoute')
    	.find('option')
    	.remove()
    	.end()
    ;
    	//.append('<option value="whatever">text</option>')
    	//.val('whatever')
    
}

function CleanShortestSelectors() {
	$('#OriginSelector')
    	.find('option')
    	.remove()
    	.end()
    ;

    $('#DestinySelector')
    	.find('option')
    	.remove()
    	.end()
    ;   
}


function CleanRouteTable(){
    var table = document.getElementById('tblMyRoutes');
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
}

function CleanRouteForm(){
    $('#tboxRtName').val("");
    $('#tboxRtPrice').val("");
    $('#tboxRtTraffic').val("");
    $('#tboxRtColor').val("");
}