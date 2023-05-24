/*
    findAvailableAppointmetnsByDoctor sends 
    a request on server searching for a doctor, 
    giving as input some information about the 
    doctor. If we find the doctor, we return as 
    a response an array of the available appointments
    so that the patient can book one.

    Only patients have access on this service, so they
    must be connected with their credentials to perform
    the request

    example response:

*/

const doctor = {
    "doctorFirstname": "Anastasia",
    "doctorLastname": "Mallikopoulou",
    "doctorSpecialisation": "Pediatrician",
    "officeCity": "Trikala"
}

axios.post("/restapi/find_available_appointments_by_doctor", doctor)
    .then((response) => {
        console.log("Response " + JSON.stringify(response.data));
        const appointments = response.data.availableAppointments;

        appointments.forEach(appointment => {
            console.log("Available appointment: " + appointment.from + " - " + appointment.to);
            // write code here
        });
                  
    })
    .catch(function (error) {
        console.log(error);
    });
