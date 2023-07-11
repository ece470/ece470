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



actionBtn.addEventListener('click', function() {
    const medicalAction = {
        amka: document.getElementById('amka').value,
        title: document.getElementById('actionName').value,
        details: document.getElementById('actionDetails').value,
        date: document.getElementById('actionDate').value
    };
    axios.post("/restapi/execute_medical_action", medicalAction)
        .then((response) => {

            const status = response.data.status;
            if(status == 1){
                console.log("success");
            }
            else{
                console.log("fail");
            }

            console.log("status: " + status);
        })
        .catch(function (error) {
            console.log(error);
        });
    });
        
