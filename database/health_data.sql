CREATE DATABASE MedicalService;

DROP TABLE IF EXISTS Patients_Info;

CREATE TABLE Patients_Info (
   id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
   SecurityNumber int NOT NULL,
   AFMNumber int NOT NULL, 
   LastName varchar(255) NOT NULL,
   FirstName varchar(255) NOT NULL,
   Email varchar(255) NOT NULL,
   Address varchar(255) NOT NULL,
   PostalCode varchar(5) NOT NULL, 
   City varchar(255) NOT NULL,
   DateOfBirth date NOT NULL,
   PhoneNumber int NOT NULL,
   PatientTypeOfUser varchar(20) NOT NULL,
   FOREIGN KEY(PatientTypeOfUser) REFERENCES UserType(UserType)
);

CREATE TABLE PatientLogInInfo (
    PatientID int NOT NULL, 
    PatientPassword varchar(32),
    FOREIGN KEY(PatientID) REFERENCES Patients_Info(SecurityNumber)
);

CREATE TABLE History (
    PatientID int NOT NULL,
    Injures varchar(1023),
    Alergies varchar(1023),
    MedicalExams varchar(1023),
    Diseases varchar(1023),
    Vaccines varchar(1023),
    FOREIGN KEY(PatientID) REFERENCES Patients_Info(SecurityNumber)
);

CREATE TABLE DrugPrescription (
    PatientID int NOT NULL,
    DrugName varchar(1023) NOT NULL,
    DrugInstructions varchar(1023) NOT NULL,
    FOREIGN KEY(PatientID) REFERENCES Patients_Info(SecurityNumber)
);

CREATE TABLE Exams (
    PatientID int NOT NULL,
    ExamDate date,
    ExamTime time,
    ExamType varchar(1023),
    ExamInstructions varchar(1023),
    FOREIGN KEY(PatientID) REFERENCES Patients_Info(SecurityNumber)
);

CREATE TABLE Doctors (
   id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
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
   DoctorTypeOfUser varchar(20) NOT NULL, 
   FOREIGN KEY(DoctorTypeOfUser) REFERENCES UserType(UserType)
);

CREATE TABLE DoctorLogInInfo (
    DoctorID int NOT NULL,
    Username varchar(32) UNIQUE,
    DoctorPassword varchar(32),
    FOREIGN KEY(DoctorID) REFERENCES Doctors(SecurityNumber)
);

CREATE TABLE UserType (
    UserType varchar(50),
    CONSTRAINT invalidUserType CHECK (UserType IN ('Doctor', 'Patient', 'Admin'))
);

CREATE TABLE Surgery (
    PatientID int NOT NULL,
    DoctorID int NOT NULL,
    SurgeryDate date,
    SurgeryType varchar(1023),
    Instructions varchar(1023),
    Result varchar(1023),
    FOREIGN KEY(PatientID) REFERENCES Patients_Info(SecurityNumber),
    FOREIGN KEY(DoctorID) REFERENCES Doctors(SecurityNumber)
);

CREATE TABLE Appointments (
    PatientID int NOT NULL,
    DoctorID int NOT NULL,
    AppointmentDate date,
    AppointmentHour time,
    City varchar(255) NOT NULL,
    AppointmentType varchar(255) NOT NULL,
    FOREIGN KEY(PatientID) REFERENCES Patients_Info(SecurityNumber),
    FOREIGN KEY(DoctorID) REFERENCES Doctors(SecurityNumber),
    CONSTRAINT invalidCity CHECK (City IN ('Αθήνα', 'Θεσσαλονίκη', 'Πάτρα', 'Ηράκλειο', 'Λάρισα', 'Βόλος', 'Ιωάννινα', 'Τρίκαλα', 'Χαλκίδα', 'Σέρρες')),
    CONSTRAINT invalidPatient CHECK (PatientID IN (SELECT SecurityNumber FROM Patients_Info)),
    CONSTRAINT invalidDoctor CHECK (DoctorID IN (SELECT SecurityNumber FROM Doctors))
);
