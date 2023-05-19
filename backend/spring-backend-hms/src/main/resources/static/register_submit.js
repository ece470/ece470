/*
    register_submit.js contains the code 
    that will be executed when the users 
    submit their credentials
*/

const patientForm = document.getElementById('patientForm');
const doctorForm = document.getElementById('doctorForm');

doctorForm.addEventListener('submit', (e) => {
    e.preventDefault();
    console.log("Doctor Form Submitted");
    const formData = new FormData(doctorForm);
    const jsonData = {};
    formData.forEach((value, key) => { jsonData[key] = value; });

    console.log(jsonData.doctorFirstName);
});

patientForm.addEventListener('submit', (e) => {
    e.preventDefault();
    console.log("Patient Form Submitted");

    const formData = new FormData(patientForm);
    const jsonData = {};

    formData.forEach((value, key) => { jsonData[key] = value; });
    console.log(jsonData);
});
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