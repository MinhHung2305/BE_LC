ALTER TABLE public.contract
DROP COLUMN license_id;
ALTER TABLE public.contract
DROP COLUMN lc_id;
ALTER TABLE public.contract
DROP COLUMN fee_rules_id;
ALTER TABLE public.contract
DROP COLUMN proposal_release_lc_id;
ALTER TABLE public.contract
DROP COLUMN seller_corporate_id;
ALTER TABLE public.contract
    DROP COLUMN buyer_corporate_id;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN license_id integer;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN lc_id integer;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN fee_rules_id integer;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN proposal_release_lc_id integer;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN seller_corporate_id integer;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN buyer_corporate_id integer;

ALTER TABLE IF EXISTS public.contract
    ADD CONSTRAINT "FK_fee_rules_id" FOREIGN KEY (fee_rules_id)
    REFERENCES public.fee_rules (fee_rules_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.contract
    ADD CONSTRAINT "FK_seller_corporate_id" FOREIGN KEY (seller_corporate_id)
    REFERENCES public.corporate (corporate_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.contract
    ADD CONSTRAINT "FK_buyer_corporate_id" FOREIGN KEY (buyer_corporate_id)
    REFERENCES public.corporate (corporate_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.contract
    ADD CONSTRAINT "FK_lc_id" FOREIGN KEY (lc_id)
    REFERENCES public.lc_classify (lc_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.contract
    ADD CONSTRAINT "FK_license_id" FOREIGN KEY (license_id)
    REFERENCES public.license (license_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;