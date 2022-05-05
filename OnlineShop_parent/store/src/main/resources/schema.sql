DROP TABLE IF EXISTS "Categories";

CREATE TABLE "Categories"
(
    ID             int auto_increment,
    "categoryName" varchar(50) not null,
    constraint CATEGORIES_PK
        primary key (ID)
);

create unique index CATEGORIES_ID_UINDEX
    on "Categories" (ID);


DROP TABLE "Products";

create table "Products"
(
    ID         int auto_increment,
    name       varchar(100) not null,
    price      double       not null,
    rate       double       not null,
    "Category" varchar(50)  not null,
    constraint PRODUCTS_PK
        primary key (ID)

);

create unique index PRODUCTS_ID_UINDEX
    on "Products" (ID);
