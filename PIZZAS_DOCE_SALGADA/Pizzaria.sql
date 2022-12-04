CREATE DATABASE PIZZARIA;

USE PIZZARIA;

CREATE TABLE PIZZA (
ID                  INT            PRIMARY KEY
);

CREATE TABLE PIZZASALGADA (
ID_PIZZA_SAL        INT                 PRIMARY KEY,
SABOR               CHAR(40)         NOT NULL,
BORDA               CHAR(20)         NOT NULL,
PREÇO               DECIMAL(7, 2)       NOT NULL,
FOREIGN KEY(ID_PIZZA_SAL) REFERENCES PIZZA(ID)
);

CREATE TABLE PIZZADOCE (
ID_PIZZA_DOCE       INT                 PRIMARY KEY,
COBERTURA           CHAR(40)         NOT NULL,
BORDA               CHAR(20)         NOT NULL,
PREÇO               DECIMAL(7, 2)       NOT NULL,
FOREIGN KEY(ID_PIZZA_DOCE) REFERENCES PIZZA(ID)
);

CREATE TABLE Pagamento (
Id                  INT               PRIMARY KEY,
FormaPagamento      CHAR(30)        NOT NULL
);


CREATE TABLE Pix (
Id_pagamento                 INT                 PRIMARY KEY,                       
ChavePix_CPF                 CHAR(12)         NOT NULL,
ChavePix_NCelular            CHAR(11)         NOT NULL,
FOREIGN KEY(Id_pagamento)   REFERENCES  Pagamento(Id)
);



CREATE TABLE Cartao_de_Credito (
Id_Pagamento                 INT                 PRIMARY KEY,
CVC                          INT                 NOT NULL,
NumeroConta                  INT                 NOT NULL,
Datavalidacao                DATE                NOT NULL,
FOREIGN KEY(Id_pagamento)   REFERENCES  Pagamento(Id)
);

 


CREATE TABLE Funcionario (
Id                  INT                PRIMARY KEY,
Nome                CHAR(150)       NOT NULL,
CPF                 CHAR(12)           NOT NULL,
Valor               DECIMAL(7, 2)      NOT NULL
);


CREATE TABLE Cliente (
Codigo              INT                PRIMARY KEY,
Nome                CHAR(10)       NOT NULL,
Idade               INT                NOT NULL
);