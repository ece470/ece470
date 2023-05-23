

axios.get('/restapi/doctor_appointments_history')
    .then(function (response) {
        console.log("got response: " + JSON.stringify(response.data));
        const history = response.data.history;
        history.forEach(element => {
            console.log("Element: " + JSON.stringify(element))
        });
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