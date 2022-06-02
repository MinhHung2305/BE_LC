ALTER TABLE public.product
DROP COLUMN contract_id;

ALTER TABLE IF EXISTS public.product
    ADD COLUMN contract_id integer;

ALTER TABLE IF EXISTS public.product
    ADD CONSTRAINT "FK_contract_id" FOREIGN KEY (contract_id)
    REFERENCES public.contract (contract_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;