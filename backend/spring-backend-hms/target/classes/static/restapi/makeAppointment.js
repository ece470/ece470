/*
    makeAppointment sends a request on server 
    giving as input the "from" & "to" fields of
    the appointment (knows that the appointment 
    is available).
     
    Only doctors have access on this service, so they
    must be connected with their credentials to perform
    the request

    example response:

    {
        "status": 1
    }
    (for success)

    OR

    {
        "status": -1
    }
    (for fail)
*/


// const medicalAction = {
//     from: "19:30",
//     to: "20:00"
// };


setButton.addEventListener('click', function(){
    let words = document.getElementById('dates').value.split(" ");
    const request = {
        date: words[0],
        from: words[1],
        to: words[2]
    };
    
    axios.post("/restapi/make_appointment", request)
        .then((response) => {

            const status = response.data.status;
            // status = 1 -> success
            // status = -1 -> fail
            
            // write code here
            

            console.log("status: " + status);
        })
        .catch(function (error) {
            console.log(error);
        });
    });
