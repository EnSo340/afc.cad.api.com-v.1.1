create table alunos(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    ano_de_nasc int not null,
    serie_atual varchar(100),
    telefone varchar(20),
    cpf varchar(11) unique,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    primary key(id)
);