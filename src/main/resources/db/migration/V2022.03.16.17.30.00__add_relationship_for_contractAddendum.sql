ALTER TABLE IF EXISTS public.contract_addendum
    ADD COLUMN contract_id integer;
ALTER TABLE IF EXISTS public.contract_addendum
    ADD CONSTRAINT contract_id FOREIGN KEY (contract_id)
    REFERENCES public.contract (contract_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;