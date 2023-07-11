/*
example:

{
    "incomingAppointments": 
    [
        {
            "patientFirstname": "Ilias",
            "patientLastname": "Lagomatis",
            "date": "2023-05-24T08:44:19.741+00:00"
        },
        {
            "patientFirstname": "Anastasia",
            "patientLastname": "Mallikopoulou",
            "date": "2023-05-24T08:44:19.741+00:00"
        }
    ]
}

*/

//separate time from DateTime object as hh:mm:ss
function formatTime(date){
    var hours = String(date.getHours()).padStart(2, '0');
    var min = String(date.getMinutes()).padStart(2, '0');
    var sec = String(date.getSeconds()).padStart(2, '0');

    return hours + ":" +  min + ":" + sec;
}

//separate date from DateTime object as dd/mm/yyyy
function formatDate(date){
    var day = String(date.getDate()).padStart(2, '0');
    var month = String(date.getMonth()).padStart(2, '0');
    var year = String(date.getFullYear()).padStart(4, '0');

    return day + "/" +  month + "/" + year;
}


axios.get('/restapi/doctor_incoming_appointments')
    .then(function (response) {
        
        const incomingAppointments = response.data.incomingAppointments;

        incomingAppointments.forEach(element => {
            console.log("Incoming appointment for doctor: " + JSON.stringify(element))
            var tableRow = document.getElementById("upcomingDocTable");
            //add new row at the end
            var row = tableRow.insertRow(-1);
            //create 4 new cells
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
    
            //load data to the cells
            cell1.innerHTML = element.patientFirstname;
            cell2.innerHTML = element.patientLastname;
            var date = new Date(element.date);
            cell3.innerHTML = formatDate(date);
            cell4.innerHTML = formatTime(date);
        
        });

        

    })
    .catch(function (error) {
        console.log(error);
    });