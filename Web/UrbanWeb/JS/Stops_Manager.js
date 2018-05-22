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