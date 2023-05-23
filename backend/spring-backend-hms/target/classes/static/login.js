
let loginButton = document.getElementById("submitButton");

let emailField = document.getElementById("email");

let passwordField = document.getElementById("password");

loginButton.addEventListener("click", function(){
    email = emailField.value;
    password = passwordField.value;
    
    console.log("Email: " + email + " Password: " + password);

    if(!email || !password){
        console.log("Empty Fields");
    }
    else{
        const json = {
            email: email,
            password: password
        };
        axios.post('/api/v1/auth/authenticate', json)
              .then(function (response) {
                console.log(response);
                window.location.replace("/");
              })
              .catch(function (error) {
                console.log(error);
              });
    }
});