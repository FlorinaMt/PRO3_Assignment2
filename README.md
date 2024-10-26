## Database setup:

<pre> 
create schema if not exists slaughterhouse_schema;
set schema 'slaughterhouse_schema';

DROP table animal, animal_part, tray, mixed_product_tray, same_part_product_tray, mixed_product, same_part_product;


create table animal
(
    animalRegNo  serial primary key,
    animalType   varchar(20),
    animalWeight float
);

INSERT INTO animal (animalType, animalWeight) VALUES ('beef', 33.5);
INSERT INTO animal (animalType, animalWeight) VALUES ('chicken', 34.5);
INSERT INTO animal (animalType, animalWeight) VALUES ('pork', 35.5);
INSERT INTO animal (animalType, animalWeight) VALUES ('crocodile', 36.5);
INSERT INTO animal (animalType, animalWeight) VALUES ('duck', 37.5);
INSERT INTO animal (animalType, animalWeight) VALUES ('human', 38.5);

create table tray
(
    trayId      serial primary key,
    partType    varchar(40),
    totalWeight float CHECK ( tray.totalWeight <= capacity),
    capacity    float
);

INSERT into tray(partType, totalWeight, capacity) values('leg', 440.5, 500);
INSERT into tray(partType, totalWeight, capacity) values('neck', 440.5, 500);
INSERT into tray(partType, totalWeight, capacity) values('breast', 440.5, 500);
INSERT into tray(partType, totalWeight, capacity) values('back', 440.5, 500);
INSERT into tray(partType, totalWeight, capacity) values('belly', 440.5, 500);
INSERT into tray(partType, totalWeight, capacity) values('eyes', 440.5, 500);


create table animal_part
(
    partId            serial primary key,
    partType          varchar(20),
    partWeight        float,
    originAnimalRegNo bigint,
    trayId            bigint,
    FOREIGN KEY (originAnimalRegNo) references animal (animalRegNo),
    FOREIGN KEY (trayId) references tray (trayId)
);

insert into animal_part(partType, partWeight, originAnimalRegNo, trayId) values ('leg', 44, 2, 1);
insert into animal_part(partType, partWeight, originAnimalRegNo, trayId) values ('neck', 44, 3, 2);
insert into animal_part(partType, partWeight, originAnimalRegNo, trayId) values ('leg', 44, 4, 3);
insert into animal_part(partType, partWeight, originAnimalRegNo, trayId) values ('breast', 44, 4, 2);
insert into animal_part(partType, partWeight, originAnimalRegNo, trayId) values ('leg', 44, 5, 4);
insert into animal_part(partType, partWeight, originAnimalRegNo, trayId) values ('back', 44, 5, 4);
insert into animal_part(partType, partWeight, originAnimalRegNo, trayId) values ('leg', 44, 6, 5);
insert into animal_part(partType, partWeight, originAnimalRegNo, trayId) values ('belly', 44, 6, 4);
insert into animal_part(partType, partWeight, originAnimalRegNo, trayId) values ('leg', 44, 6, 6);
insert into animal_part(partType, partWeight, originAnimalRegNo, trayId) values ('eyes', 44, 6, 3);


create table same_part_product
(
    productId      serial primary key,
    partType       varchar(40),
    numberOfParts  smallint,
    weight         float,
    packageDate    date CHECK ( packageDate < same_part_product.expirationDate AND packageDate <= current_date),
    expirationDate date CHECK ( expirationDate > current_date )
);

insert into same_part_product(partType, numberOfParts, weight, packageDate, expirationDate) values ('legs', 2, 30, '01/01/1800', '01/02/2090');
insert into same_part_product(partType, numberOfParts, weight, packageDate, expirationDate) values ('legs2', 2, 30, '01/01/1800', '01/02/2090');
insert into same_part_product(partType, numberOfParts, weight, packageDate, expirationDate) values ('legs3', 2, 30, '01/01/1800', '01/02/2090');
insert into same_part_product(partType, numberOfParts, weight, packageDate, expirationDate) values ('legs4', 2, 30, '01/01/1800', '01/02/2090');
insert into same_part_product(partType, numberOfParts, weight, packageDate, expirationDate) values ('legs5', 2, 30, '01/01/1800', '01/02/2090');
insert into same_part_product(partType, numberOfParts, weight, packageDate, expirationDate) values ('legs6', 2, 30, '01/01/1800', '01/02/2090');
insert into same_part_product(partType, numberOfParts, weight, packageDate, expirationDate) values ('legs7', 2, 30, '01/01/1800', '01/02/2090');

create table mixed_product
(
    productId      serial primary key,
    productName    varchar(40),
    weight         float,
    packageDate    date CHECK ( packageDate < mixed_product.expirationDate AND packageDate <= current_date),
    expirationDate date CHECK ( expirationDate > current_date )
);

insert into mixed_product(productName, weight, packageDate, expirationDate) values ('half cow', 50, '01/01/1800', '01/02/2090');
insert into mixed_product(productName, weight, packageDate, expirationDate) values ('half chicken', 50, '01/01/1800', '01/02/2090');
insert into mixed_product(productName, weight, packageDate, expirationDate) values ('half human', 50, '01/01/1800', '01/02/2090');
insert into mixed_product(productName, weight, packageDate, expirationDate) values ('half cow', 50, '01/01/1800', '01/02/2090');
insert into mixed_product(productName, weight, packageDate, expirationDate) values ('half cow', 50, '01/01/1800', '01/02/2090');


create table same_part_product_tray
(
    trayId    bigint,
    productId bigint,
    PRIMARY KEY (trayId, productId),
    FOREIGN KEY (trayId) references tray (trayId),
    FOREIGN KEY (productId) references same_part_product (productId)
);


insert into same_part_product_tray values (2, 5);
insert into same_part_product_tray values (2,6);
insert into same_part_product_tray values (2,7);
insert into same_part_product_tray values (2,4);
insert into same_part_product_tray values (3, 5);
insert into same_part_product_tray values (3,6);
insert into same_part_product_tray values (3,7);
insert into same_part_product_tray values (4, 6);
insert into same_part_product_tray values (5, 7);
insert into same_part_product_tray values (6, 6);
insert into same_part_product_tray values (6,7);
insert into same_part_product_tray values (6, 5);

create table mixed_product_tray
(
    trayId    bigint,
    productId bigint,
    PRIMARY KEY (trayId, productId),
    FOREIGN KEY (trayId) references tray (trayId),
    FOREIGN KEY (productId) references mixed_product (productId)
);

insert into mixed_product_tray values(2, 1);
insert into mixed_product_tray values(2, 2);
insert into mixed_product_tray values(2, 3);
insert into mixed_product_tray values(3, 2);
insert into mixed_product_tray values(3,1);
insert into mixed_product_tray values(4, 2);
insert into mixed_product_tray values(4,3);
insert into mixed_product_tray values(4,4);
insert into mixed_product_tray values(5,4);
insert into mixed_product_tray values(5,5);
insert into mixed_product_tray values(6,5);
insert into mixed_product_tray values(6,4);
insert into mixed_product_tray values(6,3);


</pre>
