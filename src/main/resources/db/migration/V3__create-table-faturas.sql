CREATE TABLE IF NOT EXISTS faturas(
    id bigserial not null,
    data_vencimento date not null,
    data_processamento date not null,
    valor decimal(18,2) not null,
    valor_pago decimal(18,2),
    usuario_id bigint not null,
    primary key(id),
    constraint fk_faturas_usuario_id foreign key (usuario_id) references usuarios(id)
);