create sequence seq_product_id start with 1 increment by 50;

create table tb_products
(
    id bigint default nextval('seq_product_id') not null,
    code text not null unique ,
    name text not null,
    description text,
    image_url text,
    price numeric not null,
    primary key (id)
);