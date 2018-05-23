//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ onLoad
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

'use strict';

var selected;
var StopName = undefined;

var OriginSelected;
var DestinySelected;

window.onload = OnPageLoad;
function OnPageLoad(){

    Pick_New_Station();

    var Btn_U = $('#btn_update_Station').get(0);
    disableBtn(Btn_U);

    var Btn_U = $('#btn_updateRoute').get(0);
    disableBtn(Btn_U);

    fillStations_to_Selectors();
}

//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ E V E N T S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

function onClickCalculateShortestRoute(permiso) {
    var OriginCD = $('#OriginSelector').find(":selected").val();
    var DestinyCD = $('#DestinySelector').find(":selected").val();

    if(permiso && (OriginCD != DestinyCD)){


        var data =
        {
            origen:OriginCD,
            destino:DestinyCD
        }

        $.ajax(
        {
            type: "POST",
            url: "http://localhost:8080/UServer/api/urban/getshortestroute_img",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (data, status, jqXHR) 
            {
                var cnt = data.contenido;

                if(cnt.contenido != "null"){
                    var graph_img_in_base64 = "data:image/png;base64,"+cnt;

                    var opened = window.open("");
                    opened.document.write("<html><head><link rel = \"stylesheet\" href = \"CSS/Style.css\"><title>D I J K S T R A</title></head><body> Ruta Optima: <br> <img id = \"Shortest_IMG_View\" src = "+graph_img_in_base64+" > </body></html>");

                }
                else{
                    alert("Info: No Existe Conexion entre las 2 Rutas");
                }
                
            },

            error: function (jqXHR, status)
            {
                alert("Error de Comunicacion con /grafo_img");
            }

        });


        $.ajax(
        {
            type: "POST",
            url: "http://localhost:8080/UServer/api/urban/getshortestroute",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (data, status, jqXHR) 
            {
                $.each(data, function(codigo,Pt) {
                    var Cl = "#ff0000";
                    LinkStops(Pt.origen_latitud,Pt.origen_longitud,Pt.destino_latitud,Pt.destino_longitud,Cl);
                }); 
            },

            error: function (jqXHR, status)
            {
                alert("Error de Comunicacion con /getshortestroute");
            }
        });

    }

}

function fillStations_to_Selectors(){
    var $dropdown = $("#OriginSelector");
    $.each(StopList, function(Dts,Inf) {
        $dropdown.append($("<option />").val(Inf.codigo).text(Inf.nombre));
    });

    var $dropdown = $("#DestinySelector");
    $.each(StopList, function(Dts,Inf) {
        $dropdown.append($("<option />").val(Inf.codigo).text(Inf.nombre));
    });
}

function Test() {
    window.location.href = 'http://localhost:8080/UServer/api/urban/report_csv';
}

//(^< ............ ............ ............ ............ ............ onClickbtnbtnShow_CompleGraph_IMG

function onClickbtnbtnShow_CompleGraph_IMG(){

    const url = 'http://localhost:8080/UServer/api/urban/grafo_img';

    $.getJSON(url,function (data){

        var cnt = data.contenido;
        var graph_img_in_base64 = "data:image/png;base64,"+cnt;

        var opened = window.open("");
        opened.document.write("<html><head><link rel = \"stylesheet\" href = \"CSS/Style.css\"><title>G P R A P H</title></head><body> Grafo Completo <br> <img id = \"Graph_IMG_View\" src = "+graph_img_in_base64+" > </body></html>");

    });

}

//(^< ............ ............ ............ ............ ............ onClickbtnbtnShow_Hash_IMG

function onClickbtnbtnShow_Hash_IMG(){

    const url = 'http://localhost:8080/UServer/api/urban/tabla_hash_img';

    $.getJSON(url,function (data){

        var cnt = data.contenido;
        var hash_img_in_base64 = "data:image/png;base64,"+cnt;

        var opened = window.open("");
        opened.document.write("<html><head><link rel = \"stylesheet\" href = \"CSS/Style.css\"><title>H A S H   T A B L E</title></head><body> Tabla Hash de Rutas <br> <img id = \"Hash_IMG_View\" src = "+hash_img_in_base64+" > </body></html>");

    });

}

//(^< ............ ............ ............ ............ ............ onClickbtnbtnShow_Tree_IMG
function onClickbtnbtnShow_Tree_IMG() {

    const url = 'http://localhost:8080/UServer/api/urban/arbolito_img';

    $.getJSON(url,function (data){

        var cnt = data.contenido;
        var tree_img_in_base64 = "data:image/png;base64,"+cnt;

        var opened = window.open("");
        opened.document.write("<html><head><link rel = \"stylesheet\" href = \"CSS/Style.css\"><title>T I K E T S</title></head><body> Arbol de Tickets <br> <img id = \"Tree_IMG_View\" src = "+tree_img_in_base64+" > </body></html>");

    });
}

//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ A C T I O N S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............


function disableBtn(Btn) {
    Btn.style.background = '#c2c6ce';
    Btn.disabled = true;
    $(Btn).css({'color': 'gray'});
}

function enableBtn(Btn) {
    Btn.style.background = '#2197b5';
    Btn.disabled = false;
    $(Btn).css({'color': 'black'});
}
