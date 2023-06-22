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

    {
        "availableAppointments": 
        [
            {
                "from": "16:00",
                "to": "16:30"
            },
            {
                "from":"19:00",
                "to":"19:30"
            }
        ]
    }

*/



searchButton.addEventListener('click', function() { 
    
    const doctor = {
        doctorFirstname: document.getElementById('doctName').value,
        doctorLastname: document.getElementById('doctLastName').value,
        doctorSpecialisation:  document.getElementById('specialisation').value,
        officeCity: document.getElementById('city').value
    };

    axios.post("/restapi/find_available_appointments_by_doctor", doctor)
        .then((response) => {
            
        const appointments = response.data.availableAppointments;
    
        appointments.forEach(appointment => {

            // write code here
            let option = document.createElement("option");
            //set the value we return when selected
            option.setAttribute('from', appointment.from);
            option.setAttribute('to', appointment.to);
            option.setAttribute('date', appointment.date);
            //append the list
            let optionText = document.createTextNode(appointment.date + " " + appointment.from + " " + appointment.to );
            option.appendChild(optionText);
          
            dates.appendChild(option);

            console.log(option.date + " " + option.from + " " + option.to);
        });
                    
        })
        .catch(function (error) {
            console.log(error);
        });
});
