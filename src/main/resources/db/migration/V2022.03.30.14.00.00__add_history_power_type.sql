ALTER TABLE IF EXISTS public.power_type DROP COLUMN IF EXISTS reason_for_refusal;

CREATE TABLE public.history_power_type
(
    id serial NOT NULL,
    hostility character varying NOT NULL,
    bank_id integer NOT NULL,
    status character varying NOT NULL,
    reason_for_refusal character varying NOT NULL,
    power_id integer NOT NULL,
    created_by character varying NOT NULL,
    created_date time without time zone NOT NULL,
    last_modified_by character varying NOT NULL,
    last_modified_date time without time zone NOT NULL,
    user_change integer NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.history_power_type
    OWNER to postgre;

ALTER TABLE IF EXISTS public.history_power_type
    ADD FOREIGN KEY (power_id)
    REFERENCES public.power_type (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

