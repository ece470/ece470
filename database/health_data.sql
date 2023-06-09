--insert code here

CREATE TABLE Appointments (
    
    AppointmentDate date,
    AppointmentHour time,
    City varchar(255) NOT NULL,
    AppointmentType varchar(255) NOT NULL,
    FOREIGN KEY(PatientID) REFERENCES Patients_info(SecurityNumber) NOT NULL,
    FOREIGN KEY(DoctorID) REFERENCES Doctors(SecurityNumber) NOT NULL,
    CONSTRAINT invalidCity CHECK (City IN ('Αθήνα','Θεσσαλονίκη','Πάτρα','Ηράκλειο','Λάρισα','Βόλος','Ιωάννινα','Τρίκαλα','Χαλκίδα','Σέρρες' )),
    CONSTRAINT invalidPatient CHECK (PatientID IN (SELECT SecurityNumber FROM Patient_Info)),
    CONSTRAINT invalidDoctor CHECK (DoctorID IN(SELECT SecurityNumber FROM Doctors))
);