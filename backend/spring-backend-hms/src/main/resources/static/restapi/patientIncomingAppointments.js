axios.get('/restapi/patient_incoming_appointments')
    .then(function (response) {
        console.log("got response: " + JSON.stringify(response.data));
        const incomingAppointments = response.data.incomingAppointments;
        incomingAppointments.forEach(element => {
            console.log("incoming: " + JSON.stringify(element))
        });
    })
    .catch(function (error) {
        console.log(error);
    });