

axios.get('/restapi/doctor_appointments_history')
    .then(function (response) {
        console.log("got response: " + JSON.stringify(response.data));
        const history = response.data.history;
        history.forEach(element => {
            console.log("history: " + JSON.stringify(element))
        });
    })
    .catch(function (error) {
        console.log(error);
    });