CREATE TABLE IF NOT EXISTS usuarios(
    id bigserial not null,
    nome varchar(255) not null,
    numero_cartao varchar(20) unique not null,
    dia_vencimento varchar(20) not null,
    cep varchar(10) not null,
    rua varchar(255) not null,
    bairro varchar(100) not null,
    cidade varchar(100) not null,
    uf char(2) not null,
    numero varchar(20) not null,
    complemento varchar(100),
    ativo boolean not null,
    primary key(id)
);