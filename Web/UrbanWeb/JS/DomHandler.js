//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ onLoad
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

'use strict';

var selected;
var StopName = undefined;
//var Fst = undefined;


window.onload = OnPageLoad;
function OnPageLoad(){
    /*
    Pick_New_Station();

    var Btn_U = $('#btnUpdateStation').get(0);
    disableBtn(Btn_U);

    //(^< ............ ............ ............ T E S T
    $('#tboxName').val("Antigua");
    $('#tboxPrice').val("Q34.75");
    */

    
    var invocation = new XMLHttpRequest();
    var username = "admin";
    var password = "";
    var url = 'http://localhost:8080/apiUrban/api/hola/mundo',username,password;  
    invocation.open('GET', url, true, username,password);  
    invocation.send();
    console.log(invocation);  

    //var data = invocation.responseText;
    //var jsonResponse = JSON.parse(data);
    //console.log(jsonResponse["ID"]);

    //var PS = invocation.response;

    //alert(invocation);  

    //$('#tboxCode').val(PS);
    //console.log(PS);

    

}



//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ E V E N T S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............


//(^< ............ ............ ............ ............ ............ onClickbtnUpdateStation
function saveStationChanges() {

    var Btn_S = $('#btmAddNewStation').get(0);
    enableBtn(Btn_S);

    var Btn_U = $('#btnUpdateStation').get(0);
    disableBtn(Btn_U);

    var cnt = $('#tboxCode').val();



    if(validateAddNewStation()){

        StopList[cnt].Name = $('#tboxName').val();
        StopList[cnt].Price = $('#tboxPrice').val();
        StopList[cnt].Latitude = $('#tboxLatitude').val();
        StopList[cnt].Longitude = $('#tboxLongitude').val();
    }

    
    UpdateStopTable();
    CleanMap();
    Show_StopList_on_Map();
    CleanStopForm();
    afterADD = true;
}


//(^< ............ ............ ............ ............ ............ onClickbtmAddNewStation
function AddRow_to_Table() {

    var isValid = validateAddNewStation();

    if(isValid){

        var table = document.getElementById('tblEstaciones');
        var row = table.insertRow(1);

        var Cell_0 = row.insertCell(0);
        var Cell_1 = row.insertCell(1);
        var Cell_2 = row.insertCell(2);
        var Cell_3 = row.insertCell(3);
        var Cell_4 = row.insertCell(4);

        document.getElementById('tboxCode').value = StopList.length;

        var textCode = document.getElementById('tboxCode').value;
        var textName = document.getElementById('tboxName').value;
        var textPrice = document.getElementById('tboxPrice').value;
        var textLatitude = document.getElementById('tboxLatitude').value;
        var textLongitude = document.getElementById('tboxLongitude').value;

        var textLatitude = textLatitude.substring(0,12);
        var textLongitude = textLongitude.substring(0,12);

        Cell_0.innerHTML = textCode;
        Cell_1.innerHTML = textName;
        Cell_2.innerHTML = textPrice;
        Cell_3.innerHTML = textLatitude;
        Cell_4.innerHTML = textLongitude;

        var cnt = StopList.length;
        StopList[cnt] = new Stop(textCode,textName,textPrice,textLatitude,textLongitude);

        selected = table.getElementsByClassName('selected');
        table.onclick = Highlight;
        
        CleanMap();
        Show_StopList_on_Map();
        CleanStopForm();
        afterADD = true;

        //(^< ............ ............ ............ T E S T
        $('#tboxName').val("Antigua");
        $('#tboxPrice').val("Q34.75");
    }
}


//(^< ............ ............ ............ ............ ............ onClickbtnNewStation
function Pick_New_Station() {
    
    wantNewStop = true;
    wantNewPath = false;

    var Btn_St = document.getElementById("NewStation");
    var Btn_Pt = document.getElementById("NewRoute");
    disableBtn(Btn_St);
    enableBtn(Btn_Pt);
    CleanMap();
    document.getElementById('gboxRutas').style.display = 'none';
    document.getElementById('gboxEstaciones').style.removeProperty('display');
    Show_StopList_on_Map()
}

//(^< ............ ............ ............ ............ ............ onClickbtnNewRoute
function Built_New_Route() {

    wantNewStop = false;
    wantNewPath = true;

    var Btn_St = document.getElementById("NewStation");
    var Btn_Pt = document.getElementById("NewRoute");
    disableBtn(Btn_Pt);
    enableBtn(Btn_St);
    CleanMap();
    document.getElementById('gboxEstaciones').style.display = 'none';
    document.getElementById('gboxRutas').style.removeProperty('display');
    Show_StopList_on_Map();
}

//(^< ............ ............ ............ ............ ............ tblItemClick
function Highlight(e) {


    var Btn_S = $('#btmAddNewStation').get(0);
    disableBtn(Btn_S);

    var Btn_U = $('#btnUpdateStation').get(0);
    enableBtn(Btn_U);


    if (selected[0]) selected[0].className = '';
    e.target.parentNode.className = 'selected';
    var Fst = $(this).find(".selected td:first").html();
    FillStopInfo(Fst);
    //alert($("tr.selected td:first" ).html());
}


//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ A C T I O N S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

function UpdateStopTable() {
    CleanStopTable();
    var cnt = 0;
    var Lm = StopList.length;
    while(cnt < Lm){

        console.log(StopList[cnt]);
        VisitRows_to_Update(StopList[cnt]);

        cnt++;
    }
}


function disableBtn(Btn) {
    Btn.style.background = '#c2c6ce';
    Btn.disabled = true;
}

function enableBtn(Btn) {
    Btn.style.background = '#2197b5';
    Btn.disabled = false;
}

function FillStopInfo(ID_Stop) {
    document.getElementById('tboxCode').value = StopList[ID_Stop].Code;
    document.getElementById('tboxName').value = StopList[ID_Stop].Name;
    document.getElementById('tboxPrice').value = StopList[ID_Stop].Price;
    document.getElementById('tboxLatitude').value = StopList[ID_Stop].Latitude;
    document.getElementById('tboxLongitude').value = StopList[ID_Stop].Longitude;
}

function DeleteRow_from_Tabled() {
    document.getElementById('myTable').deleteRow(0);
}

function Update_Station() {
    CleanStopTable();
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

function VisitRows_to_Update(Std_S) {

    var table = document.getElementById('tblEstaciones');
    var row = table.insertRow(1);

    var Cell_0 = row.insertCell(0);
    var Cell_1 = row.insertCell(1);
    var Cell_2 = row.insertCell(2);
    var Cell_3 = row.insertCell(3);
    var Cell_4 = row.insertCell(4);

    Cell_0.innerHTML = Std_S.Code;
    Cell_1.innerHTML = Std_S.Name;
    Cell_2.innerHTML = Std_S.Price;
    Cell_3.innerHTML = Std_S.Latitude;
    Cell_4.innerHTML = Std_S.Longitude
}


//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ C L E A N
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............


function CleanStopTable(){
    /*
    var Parent = document.getElementById('tblEstaciones');
    while(Parent.hasChildNodes())
    {
       Parent.removeChild(Parent.firstChild);
    }
    */

    var table = document.getElementById('tblEstaciones');
    //or use :  var table = document.all.tableid;
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
}

function CleanStopForm(){
    document.getElementById('tboxCode').value = "";
    document.getElementById('tboxName').value = "";
    document.getElementById('tboxPrice').value = "";
    document.getElementById('tboxLatitude').value = "";
    document.getElementById('tboxLongitude').value = "";
}
