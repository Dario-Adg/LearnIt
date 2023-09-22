DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS program;
DROP TABLE IF EXISTS userprogram;

CREATE TABLE `user` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `Email` VARCHAR(150) NOT NULL ,
    `Password` VARCHAR(255) NOT NULL ,
    `FirstName` VARCHAR(75) NOT NULL ,
    `LastName` VARCHAR(75) NOT NULL ,
    `IsAdmin` BOOLEAN NOT NULL ,
    `IsJobSeeker` BOOLEAN NOT NULL ,
    `DateOfBirth` DATE NOT NULL ,
    `DiplomaNumber` INT NOT NULL ,
    PRIMARY KEY (`Id`))
    ENGINE = InnoDB;

CREATE TABLE `program` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `Name` VARCHAR(50) NOT NULL ,
    `Description` VARCHAR(255) NOT NULL ,
    PRIMARY KEY (`Id`))
    ENGINE = InnoDB;

CREATE TABLE `userprogram` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `UserId` BIGINT NOT NULL ,
    `ProgramID` BIGINT NOT NULL ,
    `IsValid` BOOLEAN NOT NULL ,
    `StartDateProgram` DATE NOT NULL ,
    PRIMARY KEY (`Id`))
    ENGINE = InnoDB;

ALTER TABLE `userprogram`
    ADD CONSTRAINT `UserId` FOREIGN KEY (`UserId`) REFERENCES `user`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `userprogram`
    ADD CONSTRAINT `ProgramId` FOREIGN KEY (`ProgramId`) REFERENCES `program`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;