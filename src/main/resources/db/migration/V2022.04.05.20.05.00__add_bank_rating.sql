CREATE TABLE public.bank_rating
(
    id serial NOT NULL,
    rating character varying,
    fee_amount bigint,
    fee_rules_id bigint NOT NULL,
    created_by character varying,
    created_date time without time zone,
    last_modified_by character varying,
    last_modified_date time without time zone,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.bank_rating
    OWNER to postgre;

ALTER TABLE IF EXISTS public.bank_rating
    ADD FOREIGN KEY (fee_rules_id)
    REFERENCES public.fee_rules (fee_rules_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.fee_rules
    ADD COLUMN bank_rating_id bigint;
ALTER TABLE IF EXISTS public.fee_rules
    ADD FOREIGN KEY (bank_rating_id)
    REFERENCES public.bank_rating (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;