USE estate4month2019;

create table user(
id bigint NOT NULL PRIMARY KEY auto_increment,
username VARCHAR(150) NOT NULL,
password VARCHAR(150) NOT NULL,
fullname VARCHAR(150) NOT NULL,
roleid bigint NOT NULL,
status int NOT NULL,
createddate timestamp NULL,
modifieddate timestamp NULL,
createdby VARCHAR(255) NULL,
modifiedby VARCHAR(255) NULL
);

create table role(
id bigint NOT NULL PRIMARY KEY auto_increment,
name VARCHAR(150) NOT NULL,
code VARCHAR(150) NOT NULL,
createddate timestamp NULL,
modifieddate timestamp NULL,
createdby VARCHAR(255) NULL,
modifiedby VARCHAR(255) NULL
);

create table user_role(
id bigint NOT NULL PRIMARY KEY auto_increment,
roleid bigint NOT NULL,
userid bigint NOT NULL
);

Alter TABLE user_role ADD CONSTRAINT fk_userrole_user FOREIGN KEY (userid) REFERENCES user(id);
Alter TABLE user_role ADD CONSTRAINT fk_userrole_role FOREIGN KEY (roleid) REFERENCES role(id);

CREATE TABLE building(
		id bigint NOT NULL primary key auto_increment,
		name VARCHAR(255) NULL,
        numberofbasement int NULL,
        buildingarea int NULL,
        district varchar (255) NULL,
        ward VARCHAR(100) NULL,
		street VARCHAR(100) NULL,
		structure VARCHAR(100) NULL,
        costrent int null,
        costdescription TEXT NULL,
        servicecost varchar(255) NULL,
        carcost varchar (255) NULL,
        motobikecost varchar (255) NULL,
        overtimecost varchar (255) NULL,
        electricitycost varchar (255) NULL,
        deposit varchar (255) NULL,
        payment varchar (255) NULL,
        timerent varchar (255) NULL,
        timedecorator varchar (255) NULL,
        managername varchar (255) NULL,
        type TEXT NULL,
		managerphone varchar (255) NULL,
        createddate timestamp NULL,
		modifieddate timestamp NULL,
		createdby VARCHAR(255) NULL,
		modifiedby VARCHAR(255) NULL
);

create table district(
id bigint NOT NULL PRIMARY KEY auto_increment,
name VARCHAR(255) NOT NULL,
code VARCHAR(255) NOT NULL,
createddate timestamp NULL,
modifieddate timestamp NULL,
createdby VARCHAR(255) NULL,
modifiedby VARCHAR(255) NULL
);

create table assignmentbuilding(
id bigint NOT NULL PRIMARY KEY auto_increment,
buildingid bigint NOT NULL,
userid bigint NOT NULL
);

Alter TABLE assignmentbuilding ADD CONSTRAINT fk_assignmente_user FOREIGN KEY (userid) REFERENCES user(id);
Alter TABLE assignmentbuilding ADD CONSTRAINT fk_assignment_building FOREIGN KEY (buildingid) REFERENCES building(id);