create schema TinyHomes;
set schema TinyHomes;

CREATE TABLE funder(
    funderName varchar(50) NOT NULL,
    affiliation varchar(50) NOT NULL,
    address varchar(50) NOT NULL,
    phoneNumber int NOT NULL,
    amountFunded double NOT NULL

);

CREATE TABLE location(
    state varchar(15) NOT NULL,
    city varchar(40) NOT NULL,
    streetAddress varchar(60) NOT NULL,
    serviceYears int NOT NULL
);

CREATE TABLE TinyHome(
    houseNum int NOT NULL,
    occupancyNum int NOT NULL,
    available boolean NOT NULL
);

CREATE TABLE Employment(
    employed boolean NOT NULL,
    company varchar(20) NOT NULL,
    typeOfWork varchar(20) NOT NULL,
    hourlyWage double NOT NULL,
    startDate DATE NOT NULL
);

CREATE TABLE Capactiy(
    totalCapacity int NOT NULL
);

CREATE TABLE Person(
    applicantID int NOT NULL,
    firstName varchar(20) NOT NULL,
    lastName varchar(50) NOT NULL,
    gender varchar(20) NOT NULL,
    DOB date NOT NULL,
    lengthWOPermanentHousing long varchar NOT NULL,
    dateApplied date NOT NULL,
    housingOffered boolean NOT NULL
);

CREATE TABLE AfterStay(
    lengthOfOccupancy int NOT NULL,
    goalMet boolean NOT NULL,
    housingSecured boolean NOT NULL
);