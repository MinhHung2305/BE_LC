CREATE TABLE public.commodities
(
    commodities_id serial NOT NULL,
    commodities_name character varying NOT NULL,
    contract_id integer,
    PRIMARY KEY (commodities_id)
);

ALTER TABLE IF EXISTS public.commodities
    OWNER to postgre;