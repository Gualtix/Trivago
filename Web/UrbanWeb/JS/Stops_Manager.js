'use strict';
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ S E R V E R   R E Q U E S T S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............




//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ E V E N T S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

//(^< ............ ............ ............ ............ ............ onClickbtnbtnStations_Management
function Pick_New_Station() {
    
    wantNewStop = true;
    wantNewPath = false;

    var Btn_St = document.getElementById("btnStations_Management");
    var Btn_Pt = document.getElementById("btnRoutes_Management");
    disableBtn(Btn_St);
    enableBtn(Btn_Pt);
    //CleanMap();
    document.getElementById('gboxRutas').style.display = 'none';
    document.getElementById('gboxEstaciones').style.removeProperty('display');
    //Show_StopList_on_Map()
}

//(^< ............ ............ ............ ............ ............ onClickbtn_add_btnStations_Management
function onClick_add_btnStations_Management() {

    var isValid = validateAddbtnStations_Management();

    if(isValid){

        var table = document.getElementById('tblEstaciones');
        var row = table.insertRow(1);

        var Cell_0 = row.insertCell(0);
        var Cell_1 = row.insertCell(1);
        var Cell_2 = row.insertCell(2);
        var Cell_3 = row.insertCell(3);

        document.getElementById('tboxCode').value = StopList.length;

        var textCode = document.getElementById('tboxCode').value;
        var textName = document.getElementById('tboxName').value;
        var textLatitude = document.getElementById('tboxLatitude').value;
        var textLongitude = document.getElementById('tboxLongitude').value;

        var textLatitude = textLatitude.substring(0,12);
        var textLongitude = textLongitude.substring(0,12);

        Cell_0.innerHTML = textCode;
        Cell_1.innerHTML = textName;
        Cell_2.innerHTML = textLatitude;
        Cell_3.innerHTML = textLongitude;

        var cnt = StopList.length;
        StopList[cnt] = new Stop(textCode,textName,textLatitude,textLongitude);
        updateAfter_btnStations_Management_Added(table);

        //(^< ............ To Server
        var data = StopList[cnt];
        $.ajax(
        {
            type: "POST",
            url: "http://localhost:8080/UServer/api/urban/add_new_station",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (data, status, jqXHR) 
            {
                //alert(success);
                alert("Nueva Estacion Creada Exitosamente");
            },

            error: function (jqXHR, status)
            {
                alert("Error: Estacion NO Creada");
                //console.log(jqXHR);
                //alert('fail' + status.code);
            }
        });
    }
}

//(^< ............ ............ ............ ............ ............ onLoad_add_btnStations_Management_from_Server

function onLoad_add_btnStations_Management_from_Server(code,name,lat,long) {

    var table = document.getElementById('tblEstaciones');
    var row = table.insertRow(1);

    var Cell_0 = row.insertCell(0);
    var Cell_1 = row.insertCell(1);
    var Cell_2 = row.insertCell(2);
    var Cell_3 = row.insertCell(3);

    Cell_0.innerHTML = code;
    Cell_1.innerHTML = name;
    Cell_2.innerHTML = lat;
    Cell_3.innerHTML = long;

    var cnt = StopList.length;
    StopList[cnt] = new Stop(code,name,lat,long);
    updateAfter_btnStations_Management_Added(table);

}

//(^< ............ ............ ............ ............ ............ onClickbtn_update_Station
function onClick_update_Station() {

    var Btn_S = $('#btn_add_btnStations_Management').get(0);
    enableBtn(Btn_S);

    var Btn_U = $('#btn_update_Station').get(0);
    disableBtn(Btn_U);

    var cnt = $('#tboxCode').val();

    if(validateAddbtnStations_Management()){

        StopList[cnt].nombre = $('#tboxName').val();
        StopList[cnt].latitud = $('#tboxLatitude').val();
        StopList[cnt].longitud = $('#tboxLongitude').val();

        //(^< ............ To Server
        var data = StopList[cnt];
        $.ajax(
        {
            type: "POST",
            url: "http://localhost:8080/UServer/api/urban/update_station",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (data, status, jqXHR) 
            {
                //alert(success);
                alert("Estacion Actualizada Exitosamente");
            },

            error: function (jqXHR, status)
            {
                alert("Error: Estacion NO Actualizada");
                //console.log(jqXHR);
                //alert('fail' + status.code);
            }
        });
    }

    UpdateStopTable();
    CleanMap();
    Show_StopList_on_Map();
    CleanStopForm();
    afterADD = true;
}


//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ A C T I O N S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

function updateAfter_btnStations_Management_Added(table){
    selected = table.getElementsByClassName('selected');
    table.onclick = Highlight;
    
    CleanMap();
    Show_StopList_on_Map();
    CleanStopForm();
    afterADD = true;
}


//(^< ............ ............ ............ ............ ............ tblItemClick
function Highlight(e) {

    var Btn_S = $('#btn_add_btnStations_Management').get(0);
    disableBtn(Btn_S);

    var Btn_U = $('#btn_update_Station').get(0);
    enableBtn(Btn_U);


    if (selected[0]) selected[0].className = '';
    e.target.parentNode.className = 'selected';
    var Fst = $(this).find(".selected td:first").html();
    FillStopInfo(Fst);
    //alert($("tr.selected td:first" ).html());
}

function SetLatLong_to_TextBox(LatLong) {

    document.getElementById("tboxLatitude").value = LatLong.lat();
    document.getElementById("tboxLongitude").value = LatLong.lng();

    var Lat = $('#tboxLatitude').val();
    var Long = $('#tboxLongitude').val();

    Lat = Lat.substring(0,12);
    Long = Long.substring(0,12);

    document.getElementById("tboxLatitude").value = Lat;
    document.getElementById("tboxLongitude").value = Long;
}

function FillStopInfo(ID_Stop) {
    document.getElementById('tboxCode').value = StopList[ID_Stop].codigo;
    document.getElementById('tboxName').value = StopList[ID_Stop].nombre;
    document.getElementById('tboxLatitude').value = StopList[ID_Stop].latitud;
    document.getElementById('tboxLongitude').value = StopList[ID_Stop].longitud;
}

function UpdateStopTable() {
    CleanStopTable();
    var cnt = 0;
    var Lm = StopList.length;
    while(cnt < Lm){
        VisitRows_to_Update(StopList[cnt]);
        cnt++;
    }
}

function VisitRows_to_Update(Std_S) {

    var table = document.getElementById('tblEstaciones');
    var row = table.insertRow(1);

    var Cell_0 = row.insertCell(0);
    var Cell_1 = row.insertCell(1);
    var Cell_2 = row.insertCell(2);
    var Cell_3 = row.insertCell(3);

    Cell_0.innerHTML = Std_S.codigo;
    Cell_1.innerHTML = Std_S.nombre;
    Cell_2.innerHTML = Std_S.latitud;
    Cell_3.innerHTML = Std_S.longitud;
}

//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ C L E A N
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

function CleanStopTable(){
    var table = document.getElementById('tblEstaciones');
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
}

function CleanStopForm(){
    document.getElementById('tboxCode').value = "";
    document.getElementById('tboxName').value = "";
    document.getElementById('tboxLatitude').value = "";
    document.getElementById('tboxLongitude').value = "";
}