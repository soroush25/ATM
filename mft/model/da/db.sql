create table customer(
                         id number primary key,
                         name nvarchar2(30),
                         family nvarchar2(30),
                         gender varchar2(6),
                         birth_date date,
                         city varchar2(20)
);

create table admin(
                      id number primary key,
                      name nvarchar2(30),
                      family nvarchar2(30),
                      gender varchar2(6),
                      birth_date date,
                      city varchar2(20),
                      permission nvarchar2(30)
);

create table account(
                        accountNumber number primary key,
                        balance number,
                        customer nvarchar2(30)
);

create table transaction(
                         id number primary key ,
                         amount nvarchar2(30),
                         deposit nvarchar2(30),
                         on_date date,
                         Account varchar2(20)
);

create sequence customer_seq  start with 1 increment by 1;
create sequence admin_seq  start with 1 increment by 1;
create sequence account_seq  start with 1 increment by 1;
create sequence transaction_seq  start with 1 increment by 1;