ALTER TABLE IF EXISTS public.contract_license
    ADD FOREIGN KEY (license_id)
    REFERENCES public.license (license_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.contract_license
    ADD FOREIGN KEY (contract_id)
    REFERENCES public.contract (contract_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;