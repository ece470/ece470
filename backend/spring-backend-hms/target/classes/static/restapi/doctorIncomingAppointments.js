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
axios.get('/restapi/doctor_incoming_appointments')
    .then(function (response) {
        
        const incomingAppointments = response.data.incomingAppointments;

        incomingAppointments.forEach(element => {
            console.log("Incoming appointment for doctor: " + JSON.stringify(element))
        });

    })
    .catch(function (error) {
        console.log(error);
    });