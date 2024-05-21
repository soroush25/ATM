create table Customer
(
    id         int primary key,
    fname      nvarchar2(30),
    lname      nvarchar2(30),
    nid        nvarchar2(10),
    gender     varchar2(6),
    birth_date date,
    city       varchar2(10),
    email      nvarchar2(30),
    phone      nvarchar2(30),
    address    nvarchar2(30)
);

create table Admin
(
    id         number primary key,
    fname      nvarchar2(30),
    lname      nvarchar2(30),
    nid        nvarchar2(10),
    gender     varchar2(6),
    birth_date date,
    city       varchar2(10),
    email      nvarchar2(30),
    phone      nvarchar2(30),
    address    nvarchar2(30),
    permission nvarchar2(10)
);

create table Account
(
    accountNumber number primary key,
    balance       number,
    bank          nvarchar2(10),
    accountTypes  nvarchar2(10),
    customer_id references Customer
);

create table Transaction
(
    id                     number primary key,
    amount                 nvarchar2(30),
    deposit                nvarchar2(30),
    transactionDateAndTime timestamp,
    transactionType        nvarchar2(10),
    account_id references account
);

create sequence customer_seq start with 1 increment by 1;
create sequence admin_seq start with 1 increment by 1;
create sequence account_seq start with 1 increment by 1;
create sequence transaction_seq start with 1 increment by 1;​
