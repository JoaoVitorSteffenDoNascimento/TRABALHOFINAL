CREATE DATABASE trabalho;

USE trabalho;
CREATE TABLE clientes (
idcliente INT(10) NOT NULL PRIMARY KEY auto_increment,
nome VARCHAR(45) NOT NULL,
telefone VARCHAR(20) ,
email VARCHAR(50),
cpf VARCHAR(20),
cartao varchar (15)
);

USE trabalho;
CREATE TABLE produtos (
idproduto INT(10) NOT NULL PRIMARY KEY auto_increment,
descricao VARCHAR(80) NOT NULL,
marca VARCHAR(20) ,
preco DOUBLE,
unidade VARCHAR(2),
data_validade varchar (10)
);

use trabalho;
CREATE TABLE vendas (
idvenda INT(10) NOT NULL PRIMARY KEY auto_increment,
data_venda varchar (10),
id_cliente int(10) NOT NULL,
id_produto int(10) not null,
quantidade int (10),
valor_unitario double,
valor_total double,
condicoes_pagamento varchar(20)
);
