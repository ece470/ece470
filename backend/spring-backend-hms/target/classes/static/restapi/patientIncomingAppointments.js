/*
An example of the response that server will give is:

{
    "history": 
    [
        {
            "doctorFirstname":"Anastasia",
            "doctorLastname":"Mallikopoulou",
            "doctorSpecialisation":"Cardiologist",
            "date":"2023-05-24T08:14:42.480+00:00",
            "prescriptions":
            [
                {
                    "medicine":
                    {
                        "name":"MedName",
                        "id":"h783hdn2"
                    },
                    "description":"lorem ipsum",
                    "useUntil":"2023-05-24T08:14:42.480+00:00"
                }
            ],
            "diagnoses":
            [
                {
                    "details":"High blood pressure"
                }
            ],
            "medicalActions":
            [
                {
                    "title":"some title",
                    "details":"details of medical action"
                },
                {
                    "title": "some other medical action",
                    "details": "details of medical action"
                }
            ]
        }
    ]
}

*/

axios.get('/restapi/patient_incoming_appointments')
    .then(function (response) {
        console.log("got response: " + JSON.stringify(response.data));
        const incomingAppointments = response.data.incomingAppointments;
        incomingAppointments.forEach(element => {
            console.log("incoming: " + JSON.stringify(element))
        });
    })
    .catch(function (error) {
        console.log(error);
    });