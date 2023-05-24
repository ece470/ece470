/*
example of response: 

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

axios.get('/restapi/patient_appointments_history')
    .then(function (response) {

        console.log("got response: " + JSON.stringify(response.data));

        const history = response.data.history;
        
        history.forEach(element => {
            //write your code here

            console.log("history: " + JSON.stringify(element))
        });
    
    })
    .catch(function (error) {
        console.log(error);
    });