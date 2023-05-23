

axios.get('/restapi/doctor_appointments_history')
    .then(function (response) {
        console.log("got response: " + JSON.stringify(response.data));
        /*if(response.data){
            const parsed = JSON.parse(response.data);
            console.log(parsed);
        }
        else{
            console("No data received");
        }
        */
    })
    .catch(function (error) {
        console.log(error);
    });