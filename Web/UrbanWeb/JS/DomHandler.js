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

    var Btn_U = $('#btn_updateRoute').get(0);
    disableBtn(Btn_U);
}

//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
//(^< ............ ............ ............ ............ ............ E V E N T S
//(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

function Test() {

    const url = 'http://localhost:8080/UServer/api/urban/grafo_img';

    $.getJSON(url,function (data){

        var cnt = data.contenido;
        var tree_img_in_base64 = "data:image/png;base64,"+cnt;

        var opened = window.open("");
        opened.document.write("<html><head><link rel = \"stylesheet\" href = \"CSS/Style.css\"><title>T I K E T S</title></head><body> Arbol de Tickets <br> <img id = \"Tree_IMG_View\" src = "+tree_img_in_base64+" > </body></html>");

    });

    /*

    var data =
    {
        origen:0,
        destino:7
    }

    $.ajax(
    {
        type: "POST",
        url: "http://localhost:8080/UServer/api/urban/grafo_img",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        dataType: "json",
        success: function (data, status, jqXHR) 
        {
            //alert(success);
            //alert("Estacion Actualizada Exitosamente");
            var cnt = data.contenido;
            var tree_img_in_base64 = "data:image/png;base64,"+cnt;

            var opened = window.open("");
            opened.document.write("<html><head><link rel = \"stylesheet\" href = \"CSS/Style.css\"><title>T I K E T S</title></head><body> Arbol de Tickets <br> <img id = \"Tree_IMG_View\" src = "+tree_img_in_base64+" > </body></html>");


        },

        error: function (jqXHR, status)
        {
            alert("Error: Estacion NO Actualizada");
            //console.log(jqXHR);
            //alert('fail' + status.code);
        }
    });

    */

    //CleanRouteSelector();

    //RemovePolylines();
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
