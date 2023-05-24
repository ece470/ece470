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

axios.get('/restapi/patient_incoming_appointments')
    .then(function (response) {
        
        const incomingAppointments = response.data.incomingAppointments;
        
        incomingAppointments.forEach(element => {
            console.log("Incoming appointment for patient: " + JSON.stringify(element))
        });
    
    })
    .catch(function (error) {
        console.log(error);
    });