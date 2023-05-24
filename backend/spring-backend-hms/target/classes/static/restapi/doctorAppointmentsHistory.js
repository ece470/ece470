/*
Example of a possible response:

{
    "history":
    [
        {
            "patientFirstname":"Ilias",
            "patientLastname":"Lagomatis",
            "date":"2023-05-24T08:39:43.907+00:00"
        },
        {
            "patientFirstname":"Anastasia",
            "patientLastname":"Mallikopoulou",
            "date":"2023-05-24T08:39:43.907+00:00"
        }
    ]
}
*/

axios.get('/restapi/doctor_appointments_history')
    .then(function (response) {

        const history = response.data.history;

        history.forEach(element => {
            console.log("History appointment of doctor: " + JSON.stringify(element))
        });

    })
    .catch(function (error) {
        console.log(error);
    });