//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ onLoad
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

'use strict';

var selected;
var StopName = undefined;

window.onload = OnPageLoad;
function OnPageLoad(){

    Pick_New_Station();
    var Btn_U = $('#btn_update_Station').get(0);
    disableBtn(Btn_U);
}



//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ E V E N T S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

//(^< ............ ............ ............ ............ ............ onClickcreateNewRoute

function onClick_reateNewRoute(argument) {


    if(PathList.length > 0){

        var Origin = PathList[0].Begin_Point;
        var Lm = PathList.length - 1;
        var Destiny = PathList[Lm].End_Point;

        var code = RouteList.length + 200;
        var name = Origin.Name +"-"+ Destiny.Name;
        var price = $('#tboxRtPrice').val();
        var trafico = $('#tboxRtTraffic').val();
        var color = $('#tboxRtColor').val();

        

    }
    else{


    }
}

function onClick_updateRoute(argument) {

}




//(^< ............ ............ ............ ............ ............ onClickbtnShowTree_IMG

function Test() {

    var data = "holasxD";

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/UServer/api/urban/createstation",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        dataType: "json",
        success: function (data, status, jqXHR) {

        alert(success);
        },

        error: function (jqXHR, status) {
        // error handler
        console.log(jqXHR);
        alert('fail' + status.code);
        }
    });

}


function onClickbtnShowTree_IMG() {


    //window.location.href = "http://stackoverflow.com";
    

    const url = 'http://localhost:8080/UServer/api/urban/arbolito_img';



    $.getJSON(url,function (data){


        //sconsole.log(data);
        //myjson = data;
        //impis();
        //console.log(data);
        //console.log(data.contenido);
        //var tree_img_in_base64 = "data:image/png;base64,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";
        //var tree_img_in_base64 = "data:image/png;base64,/++/+++hI8+//+//ogxw5ZkSqIDaZBV6aSGYq/lGZplndkckZ98xoICbTcIJGQAZcNmdmUc210hs35nCyJ58fgmIKX5RQGOZowxaZwYA+JaoKQwswGijBV4C6SiTUmpphMspJx9unX4KaimjDv9aaXOEBteBqmuuxgEHoLX6Kqx+yXqqBANsgCtit4FWQAEkrNbpq7HSOmtwag5w57GrmlJBASEU18ADjUYb3ADTinIttsgSB1oJFfA63bduimuqKB1keqwUhoCSK374wbujvOSu4QG6UvxBRydcpKsav++Ca6G8A6Pr1x2kVMyHwsVxUALDq/krnrhPSOzXG1lUTIoffqGR7Goi2MAxbv6O2kEG56I7CSlRsEFKFVyovDJoIRTg7sugNRDGqCJzJgcKE0ywc0ELm6KBCCJo8DIPFeCWNGcyqNFE06ToAfV0HBRgxsvLThHn1oddQMrXj5DyAQgjEHSAJMWZwS3HPxT/QMbabI/iBCliMLEJKX2EEkomBAUCxRi42VDADxyTYDVogV+wSChqmKxEKCDAYFDFj4OmwbY7bDGdBhtrnTQYOigeChUmc1K3QTnAUfEgGFgAWt88hKA6aCRIXhxnQ1yg3BCayK44EWdkUQcBByEQChFXfCB776aQsG0BIlQgQgE8qO26X1h8cEUep8ngRBnOy74E9QgRgEAC8SvOfQkh7FDBDmS43PmGoIiKUUEGkMEC/PJHgxw0xH74yx/3XnaYRJgMB8obxQW6kL9QYEJ0FIFgByfIL7/IQAlvQwEpnAC7DtLNJCKUoO/w45c44GwCXiAFB/OXAATQryUxdN4LfFiwgjCNYg+kYMIEFkCKDs6PKAIJouyGWMS1FSKJOMRB/BoIxYJIUXFUxNwoIkEKPAgCBZSQHQ1A2EWDfDEUVLyADj5AChSIQW6gu10bE/JG2VnCZGfo4R4d0sdQoBAHhPjhIB94v/wRoRKQWGRHgrhGSQJxCS+0pCZbEhAAOw==";
        
        var cnt = data.contenido;
        var tree_img_in_base64 = "data:image/png;base64,"+cnt;

        var opened = window.open("");
        opened.document.write("<html><head><link rel = \"stylesheet\" href = \"CSS/Style.css\"><title>T I K E T S</title></head><body> Arbol de Tickets <br> <img id = \"Tree_IMG_View\" src = "+tree_img_in_base64+" > </body></html>");
        //tree_img_in_base64.concat(data.contenido);
        //console.log(data.contenido);
        //console.log(tree_img_in_base64);


        //OpenIMG(tree_img_in_base64);
        //$.each(data, function (key, entry) {           
            //dropAlumno.append($('<option></option>').attr('value', entry.abbreviation).text(entry.nombre));

            //console.log(data);
            //console.log(key);
            //console.log(entry);
            //console.log("---------");
            //console.log(data);
            //console.log(entry.contenido);

            //$.each(entry.materias,function (Mt) {
              //  console.log(entry.materias);
            //})

            //for(var i in data.materias) {
              //  console.log(data.materias[i]);  // (o el campo que necesites)
            //}


        //})
    });

    //$.getJSON(url,function (data){
        //sconsole.log(data);
        //myjson = data;
        //impis();
       // $.each(data, function (key, entry) {           
            //dropAlumno.append($('<option></option>').attr('value', entry.abbreviation).text(entry.contenido));

            //console.log(data);
            //console.log(key);
            //console.log(entry);
            //console.log("---------");
          //  console.log(entry.contenido);
       // }

            //$.each(entry.materias,function (Mt) {
              //  console.log(entry.materias);
            //})

            //for(var i in data.materias) {
              //  console.log(data.materias[i]);  // (o el campo que necesites)
            //}


    
   // });
    

    //var tree_img_in_base64 = "data:image/png;base64,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";

    /*document.getElementById('img')
    .setAttribute(
        'src', 'data:image/png;base64,\'tree_img_in_base64\''
    );
    */

    //$("#img").attr("src",tree_img_in_base64);

   // $(document).ready(function (){
       // $('#ShowTree_IMG').click(function(){
               
    //});

}


/* This Function Downloads an 64_Base Image
function OpenIMG(tree_img_in_base64) {
    var link = document.createElement('a');
    //link.href = '/sites/default/files/toy/jpeg/image-1419683919_4851.jpeg';  // use realtive url 
    link.href = tree_img_in_base64;
    link.download = 'MyToy.png';
    document.body.appendChild(link);
    link.click();  
}
*/

//(^< ............ ............ ............ ............ ............ onClickbtn_update_Station
function onClick_update_Station() {

    var Btn_S = $('#btn_add_NewStation').get(0);
    enableBtn(Btn_S);

    var Btn_U = $('#btn_update_Station').get(0);
    disableBtn(Btn_U);

    var cnt = $('#tboxCode').val();

    if(validateAddNewStation()){

        StopList[cnt].nombre = $('#tboxName').val();
        StopList[cnt].latitud = $('#tboxLatitude').val();
        StopList[cnt].longitud = $('#tboxLongitude').val();
    }

    
    UpdateStopTable();
    CleanMap();
    Show_StopList_on_Map();
    CleanStopForm();
    afterADD = true;
}


//(^< ............ ............ ............ ............ ............ onClickbtn_add_NewStation
function onClick_add_NewStation() {

    var isValid = validateAddNewStation();

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
        updateAfter_NewStation_Added(table);

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
                alert(success);
            },

            error: function (jqXHR, status)
            {
                console.log(jqXHR);
                alert('fail' + status.code);
            }
        });
    }
}

//(^< ............ ............ ............ ............ ............ onLoad_add_NewStation_from_Server

function onLoad_add_NewStation_from_Server(code,name,lat,long) {

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
    updateAfter_NewStation_Added(table);

}

function updateAfter_NewStation_Added(table){
    selected = table.getElementsByClassName('selected');
    table.onclick = Highlight;
    
    CleanMap();
    Show_StopList_on_Map();
    CleanStopForm();
    afterADD = true;
}




//(^< ............ ............ ............ ............ ............ onClickbtnNewStation
function Pick_New_Station() {
    
    wantNewStop = true;
    wantNewPath = false;

    var Btn_St = document.getElementById("NewStation");
    var Btn_Pt = document.getElementById("NewRoute");
    disableBtn(Btn_St);
    enableBtn(Btn_Pt);
    //CleanMap();
    document.getElementById('gboxRutas').style.display = 'none';
    document.getElementById('gboxEstaciones').style.removeProperty('display');
    //Show_StopList_on_Map()
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


    var Btn_S = $('#btn_add_NewStation').get(0);
    disableBtn(Btn_S);

    var Btn_U = $('#btn_update_Station').get(0);
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

        //console.log(StopList[cnt]);
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
    document.getElementById('tboxCode').value = StopList[ID_Stop].codigo;
    document.getElementById('tboxName').value = StopList[ID_Stop].nombre;
    document.getElementById('tboxLatitude').value = StopList[ID_Stop].latitud;
    document.getElementById('tboxLongitude').value = StopList[ID_Stop].longitud;
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