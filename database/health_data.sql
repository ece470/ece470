CREATE DATABASE MedicalService;

DROP TABLE if EXISTS Patient_Info;

CREATE TABLE Patients_Info (
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
   SecurityNumber int NOT NULL PRIMARY KEY,
   AFMNumber int NOT NULL, 
   LastName varchar(255) NOT NULL,
   FirstName varchar(255) NOT NULL,
   Email varchar(255) NOT NULL,
   Dob date NOT NULL,
   Address varchar(255) NOT NULL,
   PostalCode varchar(5) NOT NULL, 
   City varchar(255) NOT NULL,
   DateOfBirth date NOT NULL,
   PhoneNumber int NOT NULL 
   
);

CREATE TABLE PatientLogInInfo (
    PatientPassword varchar(32),
    FOREIGN KEY(PatientID) REFERENCES Patients_info(SecurityNumber)

);

CREATE TABLE History (
    Injures varchar(1023),
    Alergies varchar(1023),
    MedicalExams varchar(1023),
    Diseases varchar(1023),
    Vaccines varchar(1023),
    FOREIGN KEY(PatientID) REFERENCES Patients_info(SecurityNumber)
);

CREATE TABLE DrugPrescription (
    DrugName varchar(1023) NOT NULL,
    DrugInstructions varchar(1023) NOT NULL,
    FOREIGN KEY(PatientID) REFERENCES Patients_info(SecurityNumber)
);

CREATE TABLE Exams (
    ExamDate date,
    ExamType varchar(1023),
    ExamInstructions varchar(1023),
    FOREIGN KEY(PatientID) REFERENCES Patients_info(SecurityNumber)
);

CREATE TABLE Surgery (
    SurgeryDate date,
    SurgeryType varchar(1023),
    Instructions varchar(1023),
    Result varchar(1023),
    FOREIGN KEY(PatientID) REFERENCES Patients_info(SecurityNumber),
    FOREIGN KEY(DoctorID) REFERENCES Doctors(SecurityNumber)
);

CREATE TABLE Doctors (
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT, #CHECK AUTOINCREMENT
   SecurityNumber int NOT NULL,
   AFMNumber int NOT NULL,
   LastName varchar(255) NOT NULL,
   FirstName varchar(255) NOT NULL,
   Email varchar(255) NOT NULL,
   DobOffice date NOT NULL,
   CenterAddress varchar(255) NOT NULL,
   PostalCode varchar(5),
   City varchar(255) NOT NULL,
   DateOfBirth date NOT NULL,
   PhoneNumber int NOT NULL,
   DoctorType varchar(255) NOT NULL,
   UserType varchar(20) NOT NULL, 
   FOREIGN KEY(UserType) REFERENCES UserType(UserType)
   
);

CREATE TABLE DoctorLogInInfo (
    Username varchar(32) UNIQUE,
    DoctorPassword varchar(32),
    FOREIGN KEY(DoctorID) REFERENCES Doctors(SecurityNumber)
);

CREATE TABLE UserType (
    UserType varchar(50),
    CONSTRAINT invalidUserType CHECK (UserType IN ('Doctor','Patient','Admin'))
);


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
