
/*
    findPatientByAmka sends a request on server 
    searching for a patient, giving as input the 
    amka (Health id) of the patient

    Only doctors have access on this service, so they
    must be connected with their credentials to perform
    the request

    example response:

    {
        "address": "123",
        "afm": "123",
        "amka": "12345",
        "city": "123",
        "email": "123",
        "firstname": "123",
        "lastname": "123",
        "tel": "123"
    }
*/

const request = {
    amka: "12345"
};

axios.post("/restapi/find_patient_by_amka", request)
    .then((response) => {

        const patient = response.data;
        // write code here
        //  

        console.log(patient);
    })
    .catch(function (error) {
        console.log(error);
    });
