create table lanche (
                        id bigserial not null primary key,
                        data_validade date,
                        peso_item float(53) not null,
                        total_item numeric(38,2),
                        lanche_molho bigint,
                        lanche_recheio bigint,
                        lanche_tipo_pao bigint,
                        pedido bigint
);


create table lanche_molho (
                              id bigserial not null primary key,
                              data_validade varchar(255),
                              peso float(53) not null,
                              preco_venda numeric(38,2),
                              tipo_molho varchar(255)
);


create table lanche_recheio (
                                id bigserial not null primary key,
                                data_validade varchar(255),
                                peso float(53) not null,
                                preco_venda numeric(38,2),
                                tipo_recheio varchar(255)
);


create table lanche_tipo_pao (
                                 id bigserial not null primary key,
                                 data_validade varchar(255),
                                 peso float(53) not null,
                                 preco_venda numeric(38,2),
                                 tipo_pao varchar(255)
);


create table pedido (
                        id bigserial not null primary key,
                        nome_cliente varchar(255),
                        status_pedido varchar(255),
                        troco numeric(38,2),
                        valor_pago numeric(38,2),
                        valor_total_servico numeric(38,2)
);


create table pizza (
                       id bigserial not null primary key,
                       data_validade date,
                       peso_item float(53) not null,
                       total_item numeric(38,2),
                       pedido bigint,
                       pizza_borda bigint,
                       pizza_molho bigint,
                       pizza_recheio bigint
);


create table pizza_borda (
                             id bigserial not null primary key,
                             data_validade varchar(255),
                             peso float(53) not null,
                             preco_venda numeric(38,2),
                             tipo_borda varchar(255)
);


create table pizza_molho (
                             id bigserial not null primary key,
                             data_validade varchar(255),
                             peso float(53) not null,
                             preco_venda numeric(38,2),
                             tipo_molho varchar(255)
);


create table pizza_recheio (
                               id bigserial not null primary key,
                               data_validade varchar(255),
                               peso float(53) not null,
                               preco_venda numeric(38,2),
                               tipo_recheio varchar(255)
);


create table salgadinho (
                            id bigserial not null primary key,
                            data_validade date,
                            peso_item float(53) not null,
                            total_item numeric(38,2),
                            pedido bigint,
                            salgadinho_massa bigint,
                            salgadinho_recheio bigint,
                            salgadinho_tipo_preparo bigint
);


create table salgadinho_massa (
                                  id bigserial not null primary key,
                                  data_validade varchar(255),
                                  peso float(53) not null,
                                  preco_venda numeric(38,2),
                                  tipo_massa varchar(255)
);


create table salgadinho_recheio (
                                    id bigserial not null primary key,
                                    data_validade varchar(255),
                                    peso float(53) not null,
                                    preco_venda numeric(38,2),
                                    tipo_recheio varchar(255)
);


create table salgadinho_tipo_preparo (
                                         id bigserial not null primary key,
                                         data_validade varchar(255),
                                         peso float(53) not null,
                                         preco_venda numeric(38,2),
                                         tipo_preparo varchar(255)
);


alter table if exists lanche
    add constraint FKibf4mi99riecoknw3son875bf
    foreign key (lanche_molho)
    references lanche_molho;


alter table if exists lanche
    add constraint FKp1vh9vrt0o6g13hgd556bvrgg
    foreign key (lanche_recheio)
    references lanche_recheio;


alter table if exists lanche
    add constraint FKqivdl8xqk8cmafxtt1o0owtg0
    foreign key (lanche_tipo_pao)
    references lanche_tipo_pao;


alter table if exists lanche
    add constraint FKkgqeeapryatmauaoor4dfmjan
    foreign key (pedido)
    references pedido;


alter table if exists pizza
    add constraint FKgf4cch6na8yqpvxu71bst2ckd
    foreign key (pedido)
    references pedido;


alter table if exists pizza
    add constraint FK4mo0qunrccow0exmuoloy5l0g
    foreign key (pizza_borda)
    references pizza_borda;


alter table if exists pizza
    add constraint FKeurvf2dt81jhutavhb5l93tfr
    foreign key (pizza_molho)
    references pizza_molho;


alter table if exists pizza
    add constraint FKmrodepkf6gskp660srni4scn3
    foreign key (pizza_recheio)
    references pizza_recheio;


alter table if exists salgadinho
    add constraint FKssfe8guerf1ruv2ekmj0t4uqn
    foreign key (pedido)
    references pedido;


alter table if exists salgadinho
    add constraint FK76dlsxvqlqf9b49yhd7mj063r
    foreign key (salgadinho_massa)
    references salgadinho_massa;


alter table if exists salgadinho
    add constraint FK5afrdmy7blltdgswrbcdf51k2
    foreign key (salgadinho_recheio)
    references salgadinho_recheio;


alter table if exists salgadinho
    add constraint FKgbywh2a8v9iaae5u7b73lltgh
    foreign key (salgadinho_tipo_preparo)
    references salgadinho_tipo_preparo;