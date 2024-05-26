create table Customer
(
    id         int primary key,
    fname      nvarchar(30),
    lname      nvarchar(30),
    gender     varchar(6),
    birth_date date,
    nid        nvarchar(10),
    city       varchar(10),
    email      nvarchar(30),
    phone      nvarchar(30),
    address    nvarchar(30),
    username   nvarchar(10),
    password   nvarchar(10)
);

create table Admin
(
    id         int primary key,
    fname      nvarchar(30),
    lname      nvarchar(30),
    gender     varchar(6),
    birth_date date,
    nid        nvarchar(10),
    city       varchar(10),
    email      nvarchar(30),
    phone      nvarchar(30),
    address    nvarchar(30),
    permission varchar(10),
    username   nvarchar(10),
    password   nvarchar(10)
);

create table Account
(
    accountNumber int primary key,
    balance       double,
    customer_id   int,
    bank          nvarchar(10),
    accountTypes  nvarchar(10),
    FOREIGN KEY (customer_id) REFERENCES Customer (id)
);

create table Transaction
(
    id                     int primary key,
    amount                 double,
    deposit                double,
    transactionDateAndTime timestamp,
    account_src             int,
    account_dst             int,
    transactionType        nvarchar(10),
    FOREIGN KEY (account_src) REFERENCES Account (accountNumber)
);
