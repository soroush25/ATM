create database MyDatabase;
create table Customer(
    id int primary key,
    fname nvarchar(30),
    lname nvarchar(30),
    gender varchar(6),
    birth_date date,
    nid nvarchar(10),
    city varchar(10),
    email nvarchar(30),
    phone nvarchar(30),
    address nvarchar(30)
);

create table Admin(
    id int primary key,
    fname nvarchar(30),
    lname nvarchar(30),
    gender varchar(6),
    birth_date date,
    nid nvarchar(10),
    city varchar(10),
    email nvarchar(30),
    phone nvarchar(30),
    address nvarchar(30),
    permission varchar(10)
);

create table Account(
    accountNumber int primary key,
    balance double,
    customer nvarchar(30),
    transaction nvarchar(20),
    bank nvarchar(10),
    accountTypes nvarchar(10)
);

create table Transaction(
    id int primary key,
    amount double,
    deposit double,
    transactionDateAndTime date,
    account nvarchar(20),
    transactionType nvarchar(10)
);