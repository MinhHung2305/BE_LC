CREATE TABLE public.product
(
    product_id serial NOT NULL,
    commodity character varying(200) NOT NULL,
    origin character varying(100) NOT NULL,
    amount int4range NOT NULL,
    unit character varying(10) NOT NULL,
    unit_price int4range NOT NULL,
    into_money int4range NOT NULL,
    contract_id int4range,
    created_date timestamp with time zone,
    created_by character varying,
    last_modified_by character varying,
    last_modified_date timestamp with time zone,
    PRIMARY KEY (product_id)
);

ALTER TABLE IF EXISTS public.product
    OWNER to postgre;
