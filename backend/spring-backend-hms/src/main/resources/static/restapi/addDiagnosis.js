/*
    addDiagnosis sends a request on server 
    searching for a patient, giving as input the 
    amka (Health id) of the patient. If we find 
    the patient, we add to them a diagnosis 
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



diagnosisBtn.addEventListener('click', function() {
    
    const diagnosis = {
        amka: document.getElementById('amka').value,
        diagnosis: document.getElementById('diagnosisDetails').value
    };

    axios.post("/restapi/add_diagnosis", diagnosis)
        .then((response) => {

            const status = response.data.status;
            // status = 1 -> success
            // status = -1 -> fail
            
            // write code here
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
       