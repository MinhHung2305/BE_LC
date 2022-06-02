CREATE TABLE public.full_progression_lc
(
    id serial NOT NULL,
    value_to bigint,
    value_from bigint,
    fee_amount bigint,
    fee_rate numeric(100),
    fee_min_value bigint,
    fee_max_value bigint,
    fee_rules_id bigint NOT NULL,
    created_by character varying,
    created_date time without time zone,
    last_modified_by character varying,
    last_modified_date time without time zone,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.full_progression_lc
    OWNER to postgre;

ALTER TABLE IF EXISTS public.full_progression_lc
    ADD FOREIGN KEY (fee_rules_id)
    REFERENCES public.fee_rules (fee_rules_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;