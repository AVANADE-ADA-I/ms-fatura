CREATE TABLE IF NOT EXISTS compras(
    id bigserial not null,
    data_compra timestamp not null,
    loja varchar(255) not null,
    valor decimal(10,2) not null,
    status_compra varchar(20) not null,
    usuario_id bigint not null,
    primary key(id),
    constraint fk_compras_usuario_id foreign key (usuario_id) references usuarios(id)
);