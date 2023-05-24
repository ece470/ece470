/*
    executeMedicalAction sends a request on server 
    searching for a patient, giving as input the 
    amka (Health id) of the patient. If we find 
    the patient, we add to them a medical action 
    that has been defined by the doctor

    Only doctors have access on this service, so they
    must be connected with their credentials to perform
    the request

    example response:

    {
        "status": 1
    }

    OR

    {
        "status": -1
    }
*/

const medicalAction = {
    amka: "12345",
    title: "title of medical action",
    details: "details of medical action"
};

axios.post("/restapi/execute_medical_action", medicalAction)
    .then((response) => {

        const status = response.data.status;
        // status = 1 -> success
        // status = -1 -> fail
        
        // write code here
          

        console.log("status: " + status);
    })
    .catch(function (error) {
        console.log(error);
    });
