function alertMessage(mesaj) {
    alert(mesaj);
}

function findAllCountryList() {
    var countryName = document.getElementById('countryName').value;

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/CountryJsonServlet?countryName="+countryName, false ); // false for synchronous request
   xmlHttp.send( null );
    console.log(xmlHttp.responseText);

    var data = JSON.parse(xmlHttp.responseText);
    var table = document.getElementById("countryTable");


    alert(countryName);

   // xmlHttp.send(countryName);

    for (var i = 0; i < data.length; i++) {
        var cityyNesne = data[i];

        var row = table.insertRow(i);

        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);

        cell1.innerHTML = cityyNesne.id;
        cell2.innerHTML = cityyNesne.city;


    }


}
