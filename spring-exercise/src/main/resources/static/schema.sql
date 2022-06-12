create table address
(
    company_number varchar(10) not null,
    locality       varchar(20) null,
    postal_code    varchar(15) not null
        primary key,
    premises       varchar(50) null,
    address_line_1 varchar(50) null,
    country        varchar(30) null,
    constraint address_company_number_uindex
        unique (company_number)
);

create table company
(
    company_number   varchar(10)  not null,
    company_type     varchar(30)  null,
    title            varchar(50)  not null,
    company_status   varchar(10)  null,
    date_of_creation varchar(20)  null,
    address_snippet  varchar(100) null,
    description      varchar(100) null,
    kind             varchar(30)  null,
    constraint company_company_number_uindex
        unique (company_number)
);

alter table company
    add primary key (company_number);

create table officers
(
    company_number       varchar(10) not null,
    name                 varchar(50) not null
        primary key,
    officer_role         varchar(30) null,
    appointed_on         varchar(20) null,
    resigned_on          varchar(20) null,
    data_of_birth        varchar(20) null,
    occupation           varchar(50) null,
    country_of_residence varchar(40) null,
    nationality          varchar(30) null
);

