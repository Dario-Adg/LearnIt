DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS program;
DROP TABLE IF EXISTS userprogram;
DROP TABLE IF EXISTS module;
DROP TABLE IF EXISTS programmodule;
DROP TABLE IF EXISTS lesson;
DROP TABLE IF EXISTS modulelesson;

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

CREATE TABLE `module` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `Name` VARCHAR(50) NOT NULL ,
    `Description` VARCHAR(255) NOT NULL ,
    PRIMARY KEY (`Id`)) ENGINE = InnoDB;

CREATE TABLE `programmodule` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `ProgramId` BIGINT NOT NULL ,
    `ModuleId` BIGINT NOT NULL ,
    PRIMARY KEY (`Id`)) ENGINE = InnoDB;

ALTER TABLE `programmodule`
ADD CONSTRAINT `Program_Id` FOREIGN KEY (`ProgramId`) REFERENCES `program`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `programmodule`
ADD CONSTRAINT `ModuleId` FOREIGN KEY (`ModuleId`) REFERENCES `module`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE `lesson` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `Name` VARCHAR(50) NOT NULL ,
    `Description` VARCHAR(255) NOT NULL ,
    PRIMARY KEY (`Id`)) ENGINE = InnoDB;

CREATE TABLE `modulelesson` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `ModuleId` BIGINT NOT NULL ,
    `LessonId` BIGINT NOT NULL ,
    PRIMARY KEY (`Id`)) ENGINE = InnoDB;

ALTER TABLE `modulelesson`
ADD CONSTRAINT `Module_Id` FOREIGN KEY (`ModuleId`) REFERENCES `module`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `modulelesson`
ADD CONSTRAINT `LessonId` FOREIGN KEY (`LessonId`) REFERENCES `lesson`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;