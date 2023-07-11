/*
example of response: 

{
    "history": 
    [
        {
            "doctorFirstname":"Anastasia",
            "doctorLastname":"Mallikopoulou",
            "doctorSpecialisation":"Cardiologist",
            "date":"2023-05-24T08:14:42.480+00:00",
            "prescriptions":
            [
                {
                    "medicine":
                    {
                        "name":"MedName",
                        "id":"h783hdn2"
                    },
                    "description":"lorem ipsum",
                    "useUntil":"2023-05-24T08:14:42.480+00:00"
                }
            ],
            "diagnoses":
            [
                {
                    "details":"High blood pressure"
                }
            ],
            "medicalActions":
            [
                {
                    "title":"some title",
                    "details":"details of medical action"
                },
                {
                    "title": "some other medical action",
                    "details": "details of medical action"
                }
            ]
        }
    ]
}

*/
function formatTime(date){
    var hours = String(date.getHours()).padStart(2, '0');
    var min = String(date.getMinutes()).padStart(2, '0');
    var sec = String(date.getSeconds()).padStart(2, '0');

    return hours + ":" +  min + ":" + sec;
}

//separate date from DateTime object as dd/mm/yyyy
function formatDate(date){
    var day = String(date.getDate()).padStart(2, '0');
    var month = String(date.getMonth()).padStart(2, '0');
    var year = String(date.getFullYear()).padStart(4, '0');

    return day + "/" +  month + "/" + year;
}

var globalHistory;
function f1(e) {
       
    var id1 = e.target.closest("tr").id;
    console.log("ID1 " + id1);
    var arrayItem = globalHistory[id1];
    console.log( arrayItem);

    if(arrayItem.diagnoses.length != 0){
        document.getElementById("actionTitle").innerHTML = 'Διάγνωση';
        document.getElementById("actionP1").innerHTML = arrayItem.diagnoses[0].details;
        document.getElementById("actionP2").innerHTML = "";
        document.getElementById("actionP3").innerHTML = "";
    }
    else if(arrayItem.medicalActions.length != 0){
        document.getElementById("actionTitle").innerHTML = 'Ιατρική Πράξη';
        document.getElementById("actionP1").innerHTML = arrayItem.medicalActions[0].title;
        document.getElementById("actionP2").innerHTML = arrayItem.medicalActions[0].details;
        document.getElementById("actionP3").innerHTML = "";
    }
    else{
        document.getElementById("actionTitle").innerHTML = 'Συνταγογράφηση';
        document.getElementById("actionP1").innerHTML = arrayItem.prescriptions[0].medicine.name + '(' + arrayItem.prescriptions[0].medicine.id + ')';
        document.getElementById("actionP2").innerHTML = arrayItem.prescriptions[0].description;
        var date = new Date(arrayItem.prescriptions[0].useUntil);
        document.getElementById("actionP3").innerHTML = "Λήψη έως: " + formatDate(date);
    }
    
}

historyPatTable.addEventListener("click", f1);
var id = 0;

axios.get('/restapi/patient_appointments_history')
    .then(function (response) {

        const history = response.data.history;
        globalHistory = history;
        history.forEach(element => {
            var tableRow = document.getElementById("historyPatTable");
            //add new row at the end
            var row = tableRow.insertRow(-1);
            //create 4 new cells
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);

            row.id = id++;  

            //load data to the cells
            cell1.innerHTML = element.doctorFirstname;
            cell2.innerHTML = element.doctorLastname;
            cell3.innerHTML = element.doctorSpecialisation
            var date = new Date(element.date);
            cell4.innerHTML = formatDate(date);

            //check if the action is diagnosis, med action or prescription and assign a code
            var code = 0;

            if(element.diagnoses.length != 0){
                cell5.innerHTML = 'Διάγνωση';
            }
            else if(element.medicalActions.length != 0){
                cell5.innerHTML = 'Ιατρική Πράξη';
            }
            else{
                cell5.innerHTML = 'Συνταγογράφηση';
            }

                console.log("History appointment of patient: " + JSON.stringify(element))
            });
    
    })
    .catch(function (error) {
        console.log(error);
    });