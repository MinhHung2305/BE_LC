CREATE TABLE public.fee_rule_bank_rating
(
    id serial NOT NULL,
    fee_amount bigint,
    fee_rules_id bigint NOT NULL,
    bank_rating_id bigint NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.fee_rule_bank_rating
    OWNER to postgre;

ALTER TABLE IF EXISTS public.bank_rating DROP COLUMN IF EXISTS fee_rules_id;
ALTER TABLE IF EXISTS public.bank_rating DROP CONSTRAINT IF EXISTS bank_rating_fee_rules_id_fkey;

ALTER TABLE IF EXISTS public.fee_rule_bank_rating
    ADD FOREIGN KEY (fee_rules_id)
    REFERENCES public.fee_rules (fee_rules_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.fee_rule_bank_rating
    ADD FOREIGN KEY (bank_rating_id)
    REFERENCES public.bank_rating (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.full_progression_lc
    ADD COLUMN full_progression_lc_code character varying NOT NULL DEFAULT '123';

ALTER TABLE IF EXISTS public.bank_rating
    ADD COLUMN bank_rating_code character varying NOT NULL DEFAULT '123';