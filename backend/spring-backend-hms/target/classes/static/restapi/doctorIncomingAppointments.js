axios.get('/restapi/doctor_incoming_appointments')
    .then(function (response) {
        const incomingAppointments = response.data.incomingAppointments;
        incomingAppointments.forEach(element => {
            console.log("incoming: " + JSON.stringify(element))
        });
    })
    .catch(function (error) {
        console.log(error);
    });