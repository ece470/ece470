
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



drugBtn.addEventListener('click', function() {

    const prescription = {
        amka: document.getElementById('amka').value,
        medicine: document.getElementById('drugCode').value,
        description: document.getElementById('drugDescription').value,
        useUntil: document.getElementById('drugDate').value
    
    };

    axios.post("/restapi/add_prescription", prescription)
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
