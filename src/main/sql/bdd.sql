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
DROP TABLE IF EXISTS `learnit`.`note_lesson`;

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
    FOREIGN KEY (UserId) REFERENCES `learnit`.`user`(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ProgramId) REFERENCES `learnit`.`program`(id) ON DELETE CASCADE ON UPDATE CASCADE
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
    FOREIGN KEY (ProgramId) REFERENCES `learnit`.`program`(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ModuleId) REFERENCES `learnit`.`module`(id) ON DELETE CASCADE ON UPDATE CASCADE
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
    FOREIGN KEY (ModuleId) REFERENCES `learnit`.`module`(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (LessonId) REFERENCES `learnit`.`lesson`(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `learnit`.`note_module` (
    `UserId` INT,
    `ProgramId` INT,
    `ModuleId` INT,
    `Note` INT(10) NULL ,
    `IsValid` INT NOT NULL ,
    FOREIGN KEY (UserId) REFERENCES `learnit`.`user`(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ModuleId) REFERENCES `learnit`.`module`(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ProgramId) REFERENCES `learnit`.`program`(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `learnit`.`note_lesson` (
    `UserId` INT,
    `ProgramId` INT,
    `ModuleId` INT,
    `LessonId` INT,
    FOREIGN KEY (UserId) REFERENCES `learnit`.`user`(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (LessonId) REFERENCES `learnit`.`lesson`(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ModuleId) REFERENCES `learnit`.`module`(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ProgramId) REFERENCES `learnit`.`program`(id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO program(Name, Description, JobIds) VALUES ('Développeur backEnd', 'Apprendre à développer en backEnd', "1,5,6,7,8,10");
INSERT INTO program(Name, Description, JobIds) VALUES ('Développeur frontEnd', 'Apprendre à développer en frontEnd', "2,5,6,7,8,10");
INSERT INTO program(Name, Description, JobIds) VALUES ('Développeur fullStack', 'Apprendre à développer en fullStack', "1,2,5,6,8,10");
INSERT INTO program(Name, Description, JobIds) VALUES ('DevOps', 'Apprendre à devenir un opérateur de développement', "1,2,7,8,9");
INSERT INTO program(Name, Description, JobIds) VALUES ('Manager de projet', 'Apprendre à devenir un manager de projet', "5,6,10");
INSERT INTO program(Name, Description, JobIds) VALUES ('UX, UI designer', 'Deviens un designer de génie', "2,8");

INSERT INTO module(Name, Description) VALUES ('Java','Apprend le Java');
INSERT INTO module(Name, Description) VALUES ('Installation environement de travail','aucune idée de ce que ça peut être');
INSERT INTO module(Name, Description) VALUES ('Html','Apprend le html');
INSERT INTO module(Name, Description) VALUES ('Css','Apprend le css');
INSERT INTO module(Name, Description) VALUES ('Php','Apprend le php');
INSERT INTO module(Name, Description) VALUES ('Php objet','Apprend le php orienté objet');
INSERT INTO module(Name, Description) VALUES ('Sql','Apprend le sql');
INSERT INTO module(Name, Description) VALUES ('Laravel','Apprend à utiliser laravel');
INSERT INTO module(Name, Description) VALUES ('Symphonie','Apprend à utiliser symphonie');

INSERT INTO lesson(Name, Description) VALUES ('Anglais','Learn the english');
INSERT INTO lesson(Name, Description) VALUES ('Français','Apprend la langue de molière');
INSERT INTO lesson(Name, Description) VALUES ('Eco',"L'économie pour devenir Le Loup de Wall Street");
INSERT INTO lesson(Name, Description) VALUES ('Droit',"Apprend des lois comme Nicolas Sarkozy");

INSERT INTO user(Email, Password, FirstName, LastName, IsAdmin, IsJobSeeker, DateOfBirth, DiplomaNumber) VALUES ('François@gmail.com','1234','François','Maestrati',1,0,'1990-01-01',NULL);
INSERT INTO user(Email, Password, FirstName, LastName, IsAdmin, IsJobSeeker, DateOfBirth, DiplomaNumber) VALUES ('Damien@gmail.com','1234','Damien','Pizarro',0,1,'2002-01-01',NULL);
INSERT INTO user(Email, Password, FirstName, LastName, IsAdmin, IsJobSeeker, DateOfBirth, DiplomaNumber) VALUES ('Dario@gmail.com','1234','Dario','AUDIGE',0,0,'2000-01-01',NULL)

INSERT INTO program_module(ProgramId, ModuleId) VALUES (1,1);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (1,7);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (2,2);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (2,3);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (2,4);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (2,5);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (2,6);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (2,7);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (2,8);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (2,9);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (3,1);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (3,2);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (3,3);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (3,4);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (3,5);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (3,6);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (3,7);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (3,8);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (3,9);
INSERT INTO program_module(ProgramId, ModuleId) VALUES (4,2);

INSERT INTO module_lesson(ModuleId, LessonId) VALUES (1,1);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (1,2);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (1,3);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (2,2);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (2,4);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (3,1);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (3,2);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (4,3);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (4,4);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (5,2);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (5,3);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (5,4);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (6,2);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (6,3);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (7,1);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (7,3);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (8,1);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (8,4);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (9,2);
INSERT INTO module_lesson(ModuleId, LessonId) VALUES (9,3);