/*
    register_submit.js contains the code 
    that will be executed when the users 
    submit their credentials
*/

 var pForm = document.getElementById("pat-form");
        var dForm = document.getElementById("doc-form");
        var btn = document.getElementById("btnBox");
        var f1 = document.getElementById("formBox");

        function redirect() {
            // window.location.href = 'http://localhost:4000'

        }

        let box = document.getElementById('btnBox');

        var dbtn = document.getElementById('doc-btn');
        var pbtn = document.getElementById('pat-btn');
        dbtn.addEventListener('click', function() { btnListener(dbtn.id) }, false);
        pbtn.addEventListener('click', function() { btnListener(pbtn.id) }, false);

        function btnListener(id) {
            box.classList.add('vhidden');
            box.addEventListener('transitionend', function(e) {
            box.classList.add('hidden');
            if (id == dbtn.id) {
                dForm.classList.remove('hidden');
            }
            else if (id == pbtn.id) {
                pForm.classList.remove('hidden');
            }
            f1.classList.remove('hidden');
            setTimeout(function () {
            f1.classList.remove('vhidden');
            }, 20);
            }, {
            capture: false,
            once: true,
            passive: false
            });
        }


        doctorSubmit = document.getElementById('doctor-submit');

        doctorSubmit.addEventListener('click', (e) => {
            var inputs = dForm.getElementsByTagName('input');
            const jsonData = {};

            for (input of inputs) {
                if (input.id == 'doctorTel') {
                    jsonData[input.id] = input.value.replaceAll(" ", "");
                }
                else {
                    jsonData[input.id] = input.value;
                }
            }

            console.log(jsonData);
            axios.post('/api/v1/auth/doctor/register', jsonData)
                .then(function (response) {
                    console.log(response);
                    window.location.replace("/");
                })
                .catch(function (error) {
                    console.log(error);
                });
        });

        patientSubmit = document.getElementById('patient-submit');

        patientSubmit.addEventListener('click', (e) => {
            var inputs = pForm.getElementsByTagName('input');
            const jsonData = {};

            for (input of inputs) {
                if (input.id == 'patientTel') {
                    jsonData[input.id] = input.value.replaceAll(" ", "");
                }
                else {
                    jsonData[input.id] = input.value;
                }
            }
            console.log(jsonData);
            axios.post('/api/v1/auth/patient/register', jsonData)
              .then(function (response) {
                console.log(response);
                window.location.replace("/");
              })
              .catch(function (error) {
                console.log(error);
              });
        });

        docPhone = document.getElementById('doctorTel');
        docPhone.addEventListener('keydown', (e) => { telString(e) });

        patPhone = document.getElementById('patientTel');
        patPhone.addEventListener('keydown', (e) => { telString(e) });

        function telString(e) {
            // Number 8 is the "Backspace" key on the keyboard
            if (e.keyCode != 8 && (e.target.value.length == 3 || e.target.value.length == 7)) {
                e.target.value += ' ';
            }
            if (e.keyCode === 8 && (e.target.value.length == 5 || e.target.value.length == 9)) {
                // Cancel the default action, if needed
                e.preventDefault();

                e.target.value = e.target.value.substring(0, e.target.value.length - 2);
            }
            if (e.target.value.length == 12) {
                e.target.value = e.target.value;
            }
        }

/*form.addEventListener('submit', (e) => {
    console.log("Form submitted");
  e.preventDefault();

  const formData = new FormData(form);
  const jsonData = {};
  formData.forEach((value, key) => { jsonData[key] = value; });

  console.log(jsonData);
  axios.post('/api/v1/auth/register', jsonData)
  .then(function (response) {
    console.log(response);
    window.location.replace("/access");
  })
  .catch(function (error) {
    console.log(error);
  });
  
});
*/