axios.get('/restapi/doctor_incoming_appointments')
    .then(function (response) {
        const history = response.data.history;
        history.forEach(element => {
            console.log("incoming: " + JSON.stringify(element))
        });
    })
    .catch(function (error) {
        console.log(error);
    });