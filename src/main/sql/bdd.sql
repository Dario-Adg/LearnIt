DROP DATABASE IF EXISTS learnit;
CREATE DATABASE learnit;

DROP TABLE IF EXISTS `learnit`.`user`;
DROP TABLE IF EXISTS `learnit`.`program`;
DROP TABLE IF EXISTS `learnit`.`userprogram`;
DROP TABLE IF EXISTS `learnit`.`module`;
DROP TABLE IF EXISTS `learnit`.`programmodule`;
DROP TABLE IF EXISTS `learnit`.`lesson`;
DROP TABLE IF EXISTS `learnit`.`modulelesson`;

CREATE TABLE `learnit`.`user` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `Email` VARCHAR(150) NOT NULL ,
    `Password` VARCHAR(255) NOT NULL ,
    `FirstName` VARCHAR(75) NOT NULL ,
    `LastName` VARCHAR(75) NOT NULL ,
    `IsAdmin` BOOLEAN NOT NULL ,
    `IsJobSeeker` BOOLEAN NOT NULL ,
    `DateOfBirth` DATE NOT NULL ,
    `DiplomaNumber` INT NULL ,
    PRIMARY KEY (`Id`))
    ENGINE = InnoDB;

CREATE TABLE `learnit`.`program` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `Name` VARCHAR(50) NOT NULL ,
    `Description` VARCHAR(255) NOT NULL ,
    PRIMARY KEY (`Id`))
    ENGINE = InnoDB;

CREATE TABLE `learnit`.`userprogram` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `UserId` BIGINT NOT NULL ,
    `ProgramId` BIGINT NOT NULL ,
    `IsValid` BOOLEAN NOT NULL ,
    `EndDateProgram` DATE NOT NULL ,
    PRIMARY KEY (`Id`))
    ENGINE = InnoDB;

ALTER TABLE `learnit`.`userprogram`
    ADD CONSTRAINT `UserId` FOREIGN KEY (`UserId`) REFERENCES `user`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `learnit`.`userprogram`
    ADD CONSTRAINT `ProgramId` FOREIGN KEY (`ProgramId`) REFERENCES `program`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE `learnit`.`module` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `Name` VARCHAR(50) NOT NULL ,
    `Description` VARCHAR(255) NOT NULL ,
    PRIMARY KEY (`Id`)) ENGINE = InnoDB;

CREATE TABLE `learnit`.`programmodule` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `ProgramId` BIGINT NOT NULL ,
    `ModuleId` BIGINT NOT NULL ,
    PRIMARY KEY (`Id`)) ENGINE = InnoDB;

ALTER TABLE `learnit`.`programmodule`
ADD CONSTRAINT `Program_Id` FOREIGN KEY (`ProgramId`) REFERENCES `program`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `learnit`.`programmodule`
ADD CONSTRAINT `ModuleId` FOREIGN KEY (`ModuleId`) REFERENCES `module`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE `learnit`.`lesson` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `Name` VARCHAR(50) NOT NULL ,
    `Description` VARCHAR(255) NOT NULL ,
    PRIMARY KEY (`Id`)) ENGINE = InnoDB;

CREATE TABLE `learnit`.`modulelesson` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `ModuleId` BIGINT NOT NULL ,
    `LessonId` BIGINT NOT NULL ,
    PRIMARY KEY (`Id`)) ENGINE = InnoDB;

ALTER TABLE `learnit`.`modulelesson`
ADD CONSTRAINT `Module_Id` FOREIGN KEY (`ModuleId`) REFERENCES `module`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `learnit`.`modulelesson`
ADD CONSTRAINT `LessonId` FOREIGN KEY (`LessonId`) REFERENCES `lesson`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE `learnit`.`noteModule` (
    `Id` BIGINT NOT NULL AUTO_INCREMENT ,
    `UserId` BIGINT NOT NULL ,
    `ModuleId` BIGINT NOT NULL ,
    `Note` INT(10) NULL ,
    `IsValid` INT NOT NULL ,
    PRIMARY KEY (id)) ENGINE = InnoDB;

ALTER TABLE `learnit`.`noteModule`
ADD CONSTRAINT `NoteModuleId` FOREIGN KEY (`ModuleId`) REFERENCES `module`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `learnit`.`noteModule`
ADD CONSTRAINT `NoteUserId` FOREIGN KEY (`UserId`) REFERENCES `user`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE;