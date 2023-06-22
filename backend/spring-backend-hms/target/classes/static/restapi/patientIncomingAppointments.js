/*
An example of the response that server will give is:

{
    "incomingAppointments": 
    [
        {
            "doctorFirstname":"Anastasia",
            "doctorLastname":"Mallikopoulou",
            "doctorSpecialisation":"Cardiologist",
            "date":"2023-05-24T08:14:42.480+00:00",
            "prescriptions": [],
            "diagnoses": [],
            "medicalActions": []
        }
    ]
}

*/
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

axios.get('/restapi/patient_incoming_appointments')
    .then(function (response) {
        
        const incomingAppointments = response.data.incomingAppointments;
        
        incomingAppointments.forEach(element => {
            console.log("Incoming appointment for patient: " + JSON.stringify(element))
            
            var tableRow = document.getElementById("upcomingPatTable");
            //add new row at the end
            var row = tableRow.insertRow(-1);
            //create 4 new cells
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);

            //load data to the cells
            cell1.innerHTML = element.doctorFirstname;
            cell2.innerHTML = element.doctorLastname;
            cell3.innerHTML = element.doctorSpecialisation
            var date = new Date(element.date);
            cell4.innerHTML = formatDate(date);
            cell5.innerHTML = formatTime(date);
            
            });
    
    })
    .catch(function (error) {
        console.log(error);
    });