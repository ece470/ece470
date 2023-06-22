
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



searchAmkaBtn.addEventListener('click', function() {
    
    const request = {
        amka: document.getElementById('amka').value
    };

    axios.post("/restapi/find_patient_by_amka", request)
        .then((response) => {

            const patient = response.data;
            if(patient){
                document.getElementById("name").innerHTML = "Όνοματεπώνυμο: " + patient.firstname + " " + patient.lastname;
                document.getElementById("amka1").innerHTML = "ΑΜΚΑ: " + patient.amka;
                document.getElementById("afm").innerHTML = "ΑΦΜ: " + patient.afm;
                document.getElementById("tel").innerHTML = "Τηλέφωνο: " + patient.tel;
                document.getElementById("address").innerHTML = "Διεύθυνση: " + patient.address;
                document.getElementById("diamoni").innerHTML = "Τόπος Διαμονής: " + patient.city;
                document.getElementById("email").innerHTML = "Email: " + patient.email;
            }
            

            console.log(patient);
        })
        .catch(function (error) {
            console.log(error);
        });
});