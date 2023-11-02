DROP DATABASE IF EXISTS learnit;
CREATE DATABASE learnit;

DROP TABLE IF EXISTS `learnit`.`user`;
DROP TABLE IF EXISTS `learnit`.`program`;
DROP TABLE IF EXISTS `learnit`.`user_program`;
DROP TABLE IF EXISTS `learnit`.`module`;
DROP TABLE IF EXISTS `learnit`.`program_module`;
DROP TABLE IF EXISTS `learnit`.`lesson`;
DROP TABLE IF EXISTS `learnit`.`module_lesson`;
DROP TABLE IF EXISTS `learnit`.`note_module`;

CREATE TABLE `learnit`.`user` (
    `Id` INT AUTO_INCREMENT PRIMARY KEY ,
    `Email` VARCHAR(150) NOT NULL UNIQUE ,
    `Password` VARCHAR(255) NOT NULL ,
    `FirstName` VARCHAR(75) NOT NULL ,
    `LastName` VARCHAR(75) NOT NULL ,
    `IsAdmin` BOOLEAN NOT NULL ,
    `IsJobSeeker` BOOLEAN NOT NULL ,
    `DateOfBirth` DATE NOT NULL ,
    `DiplomaNumber` INT NULL
);

CREATE TABLE `learnit`.`program` (
    `Id` INT AUTO_INCREMENT PRIMARY KEY ,
    `Name` VARCHAR(50) NOT NULL ,
    `Description` TEXT,
    `JobIds` VARCHAR(255)
);

CREATE TABLE `learnit`.`user_program` (
    `UserId` INT,
    `ProgramId` INT,
    `IsValid` BOOLEAN,
    `EndDateProgram` DATE,
    FOREIGN KEY (UserId) REFERENCES `learnit`.`user`(id),
    FOREIGN KEY (ProgramId) REFERENCES `learnit`.`program`(id)
);

CREATE TABLE `learnit`.`module` (
    `Id` INT AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL,
    `Description` TEXT
);

CREATE TABLE `learnit`.`program_module` (
    `ProgramId` INT,
    `ModuleId` INT,
    PRIMARY KEY (ProgramId, ModuleId),
    FOREIGN KEY (ProgramId) REFERENCES `learnit`.`program`(id),
    FOREIGN KEY (ModuleId) REFERENCES `learnit`.`module`(id)
);

CREATE TABLE `learnit`.`lesson` (
    `Id` INT AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(50) NOT NULL ,
    `Description` VARCHAR(255) NOT NULL
);

CREATE TABLE `learnit`.`module_lesson` (
    `ModuleId` INT,
    `LessonId` INT,
    PRIMARY KEY (ModuleId, LessonId),
    FOREIGN KEY (ModuleId) REFERENCES `learnit`.`module`(id),
    FOREIGN KEY (LessonId) REFERENCES `learnit`.`lesson`(id)
);

CREATE TABLE `learnit`.`note_module` (
    `UserId` INT,
    `ModuleId` INT,
    `Note` INT(10) NULL ,
    `IsValid` INT NOT NULL ,
    FOREIGN KEY (UserId) REFERENCES `learnit`.`user`(id),
    FOREIGN KEY (ModuleId) REFERENCES `learnit`.`module`(id)
);