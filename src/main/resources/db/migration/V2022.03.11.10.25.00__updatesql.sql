ALTER TABLE public.contract
DROP COLUMN license_id;

ALTER TABLE public.product
DROP COLUMN amount;

ALTER TABLE IF EXISTS public.product
    ADD COLUMN amount integer;

ALTER TABLE public.product
DROP COLUMN unit_price;

ALTER TABLE IF EXISTS public.product
    ADD COLUMN unit_price integer;

ALTER TABLE public.product
DROP COLUMN into_money;

ALTER TABLE IF EXISTS public.product
    ADD COLUMN into_money integer;

CREATE TABLE public.contract_license
(
    id serial NOT NULL,
    license_id integer,
    contract_id integer,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.contract_license
    OWNER to postgre;