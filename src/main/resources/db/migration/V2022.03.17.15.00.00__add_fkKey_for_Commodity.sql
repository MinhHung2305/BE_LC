ALTER TABLE IF EXISTS public.contract
    ADD COLUMN status integer;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN buyer_digital_signing_date time with time zone;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN buyer_digital_signature integer;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN seller_confirmation_date time with time zone;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN seller_verifier integer;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN seller_digital_signing_date time with time zone;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN seller_digital_signature integer;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN "reasons_for_refusing_the_seller" character varying;
ALTER TABLE IF EXISTS public.contract
    ADD FOREIGN KEY (commodity_id)
    REFERENCES public.commodities (commodities_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;