CREATE DATABASE tesouraria;




USE tesouraria;

CREATE TABLE tbl_lancamentos (
  id int NOT NULL AUTO_INCREMENT,
  data_lanc date DEFAULT NULL,
  descricao text,
  status_ text,
  classificacao text,
  documentacao_suporte text,
  tipo varchar(100) DEFAULT NULL,
  valor decimal(10,2) DEFAULT NULL,
  banco text,
  PRIMARY KEY (`id`)
  );
CREATE TABLE `tbl_status_` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_` text,
  PRIMARY KEY (`id`)
  );
  CREATE TABLE `tbl_descricao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` text,
  PRIMARY KEY (`id`)
) ;
CREATE TABLE `tbl_documentacao_suporte` (
  `id` int NOT NULL AUTO_INCREMENT,
  `documentacao_suporte` text,
  PRIMARY KEY (`id`)
);
CREATE TABLE `tbl_classificacao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `classificacao` text,
  PRIMARY KEY (`id`)
) ;
CREATE TABLE `tbl_banco` (
  `id` int NOT NULL AUTO_INCREMENT,
  `banco` text,
  PRIMARY KEY (`id`)
) ;


