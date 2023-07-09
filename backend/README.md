#Backend

The application server will be impplemented using spring boot framework with java

The file "pom.xml" contains the dependencies of the project and whoever wants to add a new dependency (eg MySQL connection) must add it here.

The "src" directory contains the source code

First edition of the project was generated using initializr tool

----------------------------------------------------

API documentation 


Endpoints:

    /restapi/add_diagnosis
    /restapi/add_prescription
    /restapi/doctor_appointments_history
    restapi/doctor_incoming_appointments
    /restapi/execute_medical_action
    /restapi/find_available_appointments_by_doctor 
    /restapi/find_patient_by_amka
    /restapi/make_appointment 
    /restapi/patient_appointments_history
    /restapi/patient_incoming_appointments

#Usage

/restapi/add_diagnosis: doctors only, POST endpoint, to add diagnosis to a patient


/restapi/add_prescription: doctors only, POST endpoint, to add prescription to a patient


/restapi/doctor_appointments_history: doctors only, GET endpoint, to get the history of their 

appointments 


/restapi/doctor_incoming_appointments: doctors only, GET endpoint to get the incoming appointments 


/restapi/execute_medical_action: doctors only , POST endpoint to add a medical action on a patient 


/restapi/find_available_appointments_by_doctor: patients only, GET endpoint to get the available appointments of a doctor they searched for


/restapi/find_patient_by_amka: doctors only, GET endpoint to find a patient giving as an input their amka 


/restapi/make_appointment: patients only, POST endpoint to make an appointment with the doctor(after viewing available times)


/restapi/patient_appointments_history: patients only, GET endpoint, to get the history of their appointments 


/restapi/patient_incoming_appointments: patients only, GET endpoint to get all the incoming appointments
