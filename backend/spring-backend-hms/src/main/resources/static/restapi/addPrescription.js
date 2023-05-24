
/*
    addPrescription sends a request on server 
    searching for a patient, giving as input the 
    amka (Health id) of the patient. If we find 
    the patient, we add to them a prescription 
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

const prescription = {
    amka: "12345",
    medicine: "medName",
    description: "details about prescription",
    useUntil: "date"

};

axios.post("/restapi/add_prescription", prescription)
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
