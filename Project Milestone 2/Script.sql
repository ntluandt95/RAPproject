CREATE TABLE Users (
    Username varchar(255) PRIMARY KEY,
    Pass varchar(255),
    LastName varchar(255),
    FirstName varchar(255),
    UserRole varchar(255),
    DayBegin date
);


CREATE TABLE Employee (
    Username varchar(255) references Users(Username) ON DELETE CASCADE primary key
);

CREATE TABLE Customer (
    Username varchar(255) references Users(Username) ON DELETE CASCADE primary key
);

insert into users values ('employee1','12345','Sam','Sung','employee',now());
insert into users values ('employee2','12345','Sam','Sung','employee',now());
insert into users values ('customer1','12345','Ja','Va','customer',now());
insert into users values ('customer2','12345','Ja','Va','customer',now());
insert into users values ('customer3','12345','Ja','Va','customer',now());
insert into users values ('customer4','12345','Ja','Va','customer',now());

insert into employee values ('employee1');
insert into employee values ('employee2');

insert into customer values ('customer1');
insert into customer values ('customer2');

CREATE TABLE BankAccount (
    BankAccountId SERIAL PRIMARY KEY,
    Balance decimal,
    accountType varchar(255),
    Status varchar(255),
    PrimaryCustomerUsername varchar(255) references Customer(Username) on delete set null,
    SecondaryCustomerUsername varchar(255) references Customer(Username) on delete set null
);

insert into BankAccount values(default,0,'checking','approved','customer1','customer3');
insert into BankAccount values(default,0,'checking','approved','customer4',null);
insert into BankAccount values(default,0,'saving','pending','customer4',null);
insert into BankAccount values(default,0,'saving','approved','customer4','customer1');


CREATE TABLE Transaction (
    TransactionId SERIAL PRIMARY KEY,
    transactionDay date,
    transactionType varchar(255),
    Amount decimal,
    fromAccount int references BankAccount(BankAccountId),
    toAccount int references BankAccount(BankAccountId)
);

insert into transaction values(default,now(),'deposit','100',2,2);
insert into transaction values(default,now(),'withdraw','100',2,2);

UPDATE BankAccount SET status='approved' where BankAccountId=4;